package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shoukailiang.community.article.dto.ArticleDTO;
import com.shoukailiang.community.article.mapper.LabelMapper;
import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.article.req.ArticleREQ;
import com.shoukailiang.community.article.req.ArticleUserREQ;
import com.shoukailiang.community.article.req.SearchREQ;
import com.shoukailiang.community.article.vo.ArticleVO;
import com.shoukailiang.community.entities.Article;
import com.shoukailiang.community.article.mapper.ArticleMapper;
import com.shoukailiang.community.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.feign.req.UserInfoREQ;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.ArticleStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 * 文章信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-06
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Override
    public ResultVO queryPage(ArticleREQ req) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper();
        if (req.getStatus() != null) {
            queryWrapper.eq("status", req.getStatus());
        }
        if (StringUtils.isNotEmpty(req.getTitle())) {
            queryWrapper.like("title", req.getTitle());
        }
        queryWrapper.orderByDesc("gmt_modified");

        return ResultVOUtil.success(baseMapper.selectPage(req.getPage(), queryWrapper));
    }

    @Override
    public ResultVO findArticleAndLabel(String id) {
        ArticleVO articleVO = baseMapper.findArticleAndLabelById(id);
        return ResultVOUtil.success(articleVO);
    }

    @Resource
    private LabelMapper labelMapper;

    @Transactional
    @Override
    public ResultVO updateOrSave(ArticleDTO articleDTO) {
        // id 不为空，是更新
        if (StringUtils.isNotEmpty(articleDTO.getId())) {
            // 更新，先删除文章标签中间表数据
            labelMapper.deleteArticleLabel(articleDTO.getId());
            // 设置更新时间
//            article.setgmtModified(new Date());
        }
        // 如果文章是不公开的，则之直接审核通过,否则待审核
        // 0: 已删除, 1:未审核，2:审核通过，3：审核未通过

        if (articleDTO.getIspublic() == 0) { // 0：不公开，1：公开
            articleDTO.setStatus(ArticleStatusEnum.SUCCESS.getCode());
        } else {
            articleDTO.setStatus(ArticleStatusEnum.WAIT.getCode());
        }
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO,article);
        super.saveOrUpdate(article);  // 不能放最后，mp有id update,无id insert;新增结束后，会把id放到article中
        // 吧新增标签的数据放到中间表中
        if (CollectionUtils.isNotEmpty(articleDTO.getLabelIds())) {
            labelMapper.saveArticleLabel(articleDTO.getId(), articleDTO.getLabelIds());
        }
        return ResultVOUtil.success(articleDTO.getId());
    }

    @Override
    public ResultVO updateStatus(String id, ArticleStatusEnum statusEnum) {
        Article article = baseMapper.selectById(id);
        article.setStatus(statusEnum.getCode());
        baseMapper.updateById(article);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO findListByUserId(ArticleUserREQ req) {
        if (StringUtils.isBlank(req.getUserId())) {
            return ResultVOUtil.error("无效的用户");
        }
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("user_id", req.getUserId());
        if (req.getIsPublic() != null) {
            // 注意数据库ispublic
            articleQueryWrapper.eq("ispublic", req.getIsPublic());
        }
        // 排序
        articleQueryWrapper.orderByDesc("gmt_modified");
        IPage<Article> page = baseMapper.selectPage(req.getPage(), articleQueryWrapper);
        return ResultVOUtil.success(page);

    }

    /**
     * 更新点赞数，不需要更新时间
     *
     * @param id
     * @param count 有增有减
     * @return
     */
    @Override
    public ResultVO updateThumhup(String id, int count) {
        if (count != -1 && count != 1) {
            return ResultVOUtil.error("无效操作");
        }
        if (StringUtils.isBlank(id)) {
            return ResultVOUtil.error("无效操作");
        }
        Article article = baseMapper.selectById(id);
        if (null == article) {
            return ResultVOUtil.error("文章不存在");
        }
        if (article.getThumhup() <= 0 && count == -1) {
            return ResultVOUtil.error("无效操作");
        }
        article.setThumhup(article.getThumhup() + count);
        baseMapper.updateById(article);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updateViewCount(String id) {
        if (StringUtils.isBlank(id)) {
            return ResultVOUtil.error("无效操作");
        }
        Article article = baseMapper.selectById(id);
        if (article == null) {
            return ResultVOUtil.error("文章不存在");
        }
        article.setViewCount(article.getViewCount() + 1);
        baseMapper.updateById(article);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO findListByLabelIdOrCategoryId(ArticleListREQ req) {
        // 查询文章列表
        return ResultVOUtil.success(baseMapper.findListByLabelIdOrCategoryId(req.getPage(), req));
    }

    @Override
    public ResultVO getArticleTotal() {
        // 查询总文章数
        QueryWrapper<Article> wrapper = new QueryWrapper();
        // 状态是审核通过
        wrapper.eq("status", ArticleStatusEnum.SUCCESS.getCode()); // 公开
        wrapper.eq("ispublic", 1);
        int total = baseMapper.selectCount(wrapper);
        return ResultVOUtil.success(total);
    }

    @Override
    public ResultVO selectCategoryTotal() {
        List<Map<String, Object>> maps = baseMapper.selectCategoryTotal();
        // 将分类名称提取到集合
        List<Object> namelist = new ArrayList<>();
        for (Map<String,Object> map:maps){
            namelist.add(map.get("name"));
        }
        // 封装响应数据
        Map<String, Object> data = new HashMap<>();
        data.put("nameAndValueList",maps);
        data.put("nameList",namelist);
        return ResultVOUtil.success(data);
    }

    @Override
    public ResultVO selectMonthArticleTotal() {
        List<Map<String, Object>> maps = baseMapper.selectMonthAritcleTotal();
        // 将年月提取到集合中
        List<Object> yearMonthList = new ArrayList<>();
        // 将每个月的文章数提取集合中
        List<Object> aritcleTotalList = new ArrayList<>();
        for(Map<String, Object> map: maps){
            yearMonthList.add( map.get("year_month") );
            aritcleTotalList.add( map.get("total") );
        }
        Map<String, Object> data = new HashMap<>();
        data.put("yearMonthList", yearMonthList);
        // aritcleTotalList 前端也是这个字段
        data.put("aritcleTotalList", aritcleTotalList);
        return ResultVOUtil.success(data);
    }

    @Override
    public boolean updateUserInfo(UserInfoREQ req) {
        return baseMapper.updateUserInfo(req);
    }

    /**
     * search
     * @param
     * @return
     */
    @Override
    public ResultVO queryPage(String title, Long current, Long size) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper();
        if (StringUtils.isNotEmpty(title)) {
            queryWrapper.like("title", title).or().like("nick_name",title);
        }
        queryWrapper.eq("status",2).eq("ispublic",1);
        queryWrapper.orderByDesc("gmt_modified");


        Page<Article> articlePage = new Page<Article>().setCurrent(current).setSize(size);
        Page<Article> articlePage1 = baseMapper.selectPage(articlePage, queryWrapper);
        return ResultVOUtil.success(articlePage1);
    }

    @Override
    public ResultVO findHotList() {
        QueryWrapper<Article> queryWrapper = new QueryWrapper();
        queryWrapper.orderByDesc("thumhup+view_count").last("limit 10");
        return ResultVOUtil.success(baseMapper.selectList(queryWrapper));
    }


}
