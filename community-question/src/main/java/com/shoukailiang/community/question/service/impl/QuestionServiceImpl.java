package com.shoukailiang.community.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.entities.Question;
import com.shoukailiang.community.feign.IFeignArticleController;
import com.shoukailiang.community.feign.req.UserInfoREQ;
import com.shoukailiang.community.question.dto.QuestionDTO;
import com.shoukailiang.community.question.mapper.QuestionMapper;
import com.shoukailiang.community.question.req.QuestionUserREQ;
import com.shoukailiang.community.question.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.question.vo.QuestionVO;
import com.shoukailiang.community.util.base.BaseRequest;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 问题信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Autowired
    private IFeignArticleController feignArticleController;

    @Override
    public ResultVO findHotList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        //  非删除问题
        wrapper.in("status", Arrays.asList(1, 2));
        // 按回复数降序排列，
        wrapper.orderByDesc("reply");
        // 分页查询热门回答列表
        IPage<Question> data = baseMapper.selectPage(req.getPage(), wrapper);
        return ResultVOUtil.success(data);
    }

    @Override
    public ResultVO findNewList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        wrapper.orderByDesc("gmt_modified");
        IPage<Question> data = baseMapper.selectPage(req.getPage(), wrapper);
        return ResultVOUtil.success(data);
    }

    @Override
    public ResultVO findWaitList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        // 查询回复数为0，按  问题创建时间 降序排列
        wrapper.eq("reply", 0);
        wrapper.orderByDesc("gmt_create");
        IPage<Question> data = baseMapper.selectPage(req.getPage(), wrapper);
        return ResultVOUtil.success(data);
    }

    @Override
    public ResultVO findListByLabelId(BaseRequest<Question> req, String labelId) {
        if (StringUtils.isEmpty(labelId)) {
            return ResultVOUtil.success("标签ID不能为空");
        }
        IPage<Question> data = baseMapper.findListByLabelId(req.getPage(), labelId);
        return ResultVOUtil.success(data);
    }

    @Override
    public ResultVO findById(String id) {
        // 1. 查询问题详情与标签ids
        QuestionVO questionVO = baseMapper.findQuestionAndLabelIdsById(id);
        if (questionVO == null) {
            return ResultVOUtil.error("未查询到相关问题信息");
        }
        //  Feign 程调用 Article 微服务查询标签信息
        if (CollectionUtils.isNotEmpty(questionVO.getLabelIds())) {
            List<Label> labelListByIds = feignArticleController.getLabelListByIds(questionVO.getLabelIds());
            questionVO.setLabelList(labelListByIds);
        }
        return ResultVOUtil.success(questionVO);
    }

    @Override
    public ResultVO updateViewCount(String id) {
        if (StringUtils.isBlank(id)) {
            return ResultVOUtil.error("无效操作");
        }
        Question question = baseMapper.selectById(id);
        if (question == null) {
            return ResultVOUtil.error("问题不存在");
        }
        question.setViewCount(question.getViewCount() + 1);
        // 不用设置更新时间，更新时间是编辑后才设置
        baseMapper.updateById(question);
        return ResultVOUtil.success();
    }

    @Transactional
    @Override
    public ResultVO updateOrSave(QuestionDTO questionDTO) {
        // 1. id 不为空，是更新操作
        if (StringUtils.isNotEmpty(questionDTO.getId())) {
            // 更新：先删除问题标签中间表数据，再新增到中间表
            baseMapper.deleteQuestionLabel(questionDTO.getId());
        }
        // 2. 更新或保存到文章信息表（不能放到最后，因为新增后，要返回新增id到question.id里）
        Question question = new Question();
        BeanUtils.copyProperties(questionDTO,question);
        super.saveOrUpdate(question);
        // 3. 新增到文章标签中间表
        if (CollectionUtils.isNotEmpty(questionDTO.getLabelIds())) {
            baseMapper.saveQuestionLabel(question.getId(), questionDTO.getLabelIds());
        }

        return ResultVOUtil.success(question.getId());

    }

    @Override
    public ResultVO deleteById(String id) {
        return this.updateStatus(id, 0); // 0 已删除
    }

    @Override
    public ResultVO updateThumhup(String id, int count) {
        if (count != -1 && count != 1) {
            return ResultVOUtil.error("无效操作");
        }
        if (StringUtils.isBlank(id)) {
            return ResultVOUtil.error("无效操作");
        }
        Question question = baseMapper.selectById(id);
        if (question == null) {
            return ResultVOUtil.error("问题不存在");
        }
        if (question.getThumhup() <= 0 && count == -1) {
            return ResultVOUtil.error("无效操作");
        }
        question.setThumhup(question.getThumhup() + count);
        baseMapper.updateById(question);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO findListByUserId(QuestionUserREQ req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return ResultVOUtil.error("无效用户信息");
        }
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        // 根据用户id查询
        wrapper.eq("user_id", req.getUserId());
        // 排序
        wrapper.orderByDesc("gmt_modified");
        IPage<Question> data = baseMapper.selectPage(req.getPage(), wrapper);
        return ResultVOUtil.success(data);
    }

    @Override
    public ResultVO getQuestionTotal() {
        // 查询总提问数
        QueryWrapper<Question> wrapper = new QueryWrapper();
        wrapper.in("status", Arrays.asList(1, 2));
        int total = baseMapper.selectCount(wrapper);
        return ResultVOUtil.success(total);
    }


    public ResultVO updateStatus(String id, Integer status) {
        Question question = baseMapper.selectById(id);
        question.setStatus(status);
        baseMapper.updateById(question);
        return ResultVOUtil.success();
    }

    @Override
    public boolean updateUserInfo(UserInfoREQ req) {
        return baseMapper.updateUserInfo(req);
    }

}
