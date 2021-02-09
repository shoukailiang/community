package com.shoukailiang.community.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.entities.Question;
import com.shoukailiang.community.question.mapper.QuestionMapper;
import com.shoukailiang.community.question.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.BaseRequest;
import com.shoukailiang.community.util.base.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;

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

    @Override
    public Result findHotList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        //  非删除问题
        wrapper.in("status", Arrays.asList(1, 2));
        // 按回复数降序排列，
        wrapper.orderByDesc("reply");
        // 分页查询热门回答列表
        IPage<Question> data = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(data);
    }

    @Override
    public Result findNewList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        wrapper.orderByDesc("update_date");
        IPage<Question> data = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(data);
    }

    @Override
    public Result findWaitList(BaseRequest<Question> req) {
        QueryWrapper<Question> wrapper = new QueryWrapper<>();
        wrapper.in("status", Arrays.asList(1, 2));
        // 查询回复数为0，按  问题创建时间 降序排列
        wrapper.eq("reply", 0);
        wrapper.orderByDesc("create_date");
        IPage<Question> data = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(data);
    }

    @Override
    public Result findListByLabelId(BaseRequest<Question> req, String labelId) {
        if(StringUtils.isEmpty(labelId)) {
            return Result.ok("标签ID不能为空");
        }
        IPage<Question> data = baseMapper.findListByLabelId(req.getPage(), labelId);
        return Result.ok(data);
    }
}
