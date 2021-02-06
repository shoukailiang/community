package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.article.req.ArticleREQ;
import com.shoukailiang.community.article.req.ArticleUserREQ;
import com.shoukailiang.community.entities.Article;
import com.shoukailiang.community.article.mapper.ArticleMapper;
import com.shoukailiang.community.article.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.Result;
import com.shoukailiang.community.util.enums.ArticleStatusEnum;
import feign.QueryMap;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.text.CollatorUtilities;

import java.util.Collections;
import java.util.Date;

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
    QueryWrapper<Article> queryWrapper = new QueryWrapper();
    @Override
    public Result queryPage(ArticleREQ req) {
        if(req.getStatus()!=null){
            queryWrapper.eq("status",req.getStatus());
        }
        if(StringUtils.isNotEmpty(req.getTitle())){
            queryWrapper.like("title",req.getTitle());
        }
        queryWrapper.orderByDesc("update_date");

        return Result.ok(baseMapper.selectPage(req.getPage(),queryWrapper));


    }

    @Override
    public Result findArticleAndLabel(String id) {
        Article article = baseMapper.findArticleAndLabelById(id);
        return Result.ok(article);
    }

    @Transactional
    @Override
    public Result updateOrSave(Article article) {
        // id 不为空，是更新
        if(StringUtils.isNotEmpty(article.getId())){
            // 更新，先删除文章标签中间表数据
            baseMapper.deleteArticleLabel(article.getId());
            // 设置更新时间
            article.setUpdateDate(new Date());
        }
        // 如果文章是不公开的，则之直接审核通过,否则待审核
        // 0: 已删除, 1:未审核，2:审核通过，3：审核未通过

        if(article.getIspublic()==0){ // 0：不公开，1：公开
            article.setStatus(ArticleStatusEnum.SUCCESS.getCode());
        }else {
            article.setStatus(ArticleStatusEnum.WAIT.getCode());
        }
        super.saveOrUpdate(article);  // 不能放最后，mp有id update,无id insert;新增结束后，会把id放到article中
        // 吧新增标签的数据放到中间表中
        if(CollectionUtils.isNotEmpty(article.getLabelIds())){
            baseMapper.saveArticleLabel(article.getId(),article.getLabelIds());
        }
        return Result.ok(article.getId());
    }

    @Override
    public Result updateStatus(String id, ArticleStatusEnum statusEnum) {
        Article article = baseMapper.selectById(id);
        article.setStatus(statusEnum.getCode());
        article.setUpdateDate(new Date());
        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result findListByUserId(ArticleUserREQ req) {
        if(StringUtils.isBlank(req.getUserId())){
            return Result.error("无效的用户");
        }
        QueryWrapper<Article> articleQueryWrapper = new QueryWrapper<>();
        articleQueryWrapper.eq("user_id", req.getUserId());
        if(req.getIsPublic()!=null){
            // 注意数据库ispublic
            articleQueryWrapper.eq("ispublic", req.getIsPublic());
        }
        // 排序
        articleQueryWrapper.orderByDesc("update_date");
        IPage<Article> page = baseMapper.selectPage(req.getPage(), articleQueryWrapper);
        return Result.ok(page);

    }

    /**
     *  更新点赞数，不需要更新时间
     * @param id
     * @param count 有增有减
     * @return
     */
    @Override
    public Result updateThumhup(String id, int count) {
        if(count!=-1&&count!=1){
            return Result.error("无效操作");
        }
        if(StringUtils.isBlank(id)){
            return Result.error("无效操作");
        }
        Article article = baseMapper.selectById(id);
        if(null==article){
            return Result.error("文章不存在");
        }
        if(article.getThumhup()<=0&&count==-1){
            return Result.error("无效操作");
        }
        article.setThumhup(article.getThumhup()+count);
        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result updateViewCount(String id) {
        if(StringUtils.isBlank(id)) {
            return Result.error("无效操作");
        }
        Article article = baseMapper.selectById(id);
        if(article == null) {
            return Result.error("文章不存在");
        }
        article.setViewCount( article.getViewCount() + 1);
        baseMapper.updateById(article);
        return Result.ok();
    }

    @Override
    public Result findListByLabelIdOrCategoryId(ArticleListREQ req) {
        // 查询文章列表
        return Result.ok(baseMapper.findListByLabelIdOrCategoryId(req.getPage(), req));
    }
}
