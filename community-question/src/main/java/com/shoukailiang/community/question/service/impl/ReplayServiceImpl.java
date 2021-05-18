package com.shoukailiang.community.question.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shoukailiang.community.entities.Question;
import com.shoukailiang.community.entities.Replay;
import com.shoukailiang.community.question.mapper.QuestionMapper;
import com.shoukailiang.community.question.mapper.ReplayMapper;
import com.shoukailiang.community.question.service.IReplayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.question.vo.ReplayVO;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 回答信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Service
public class ReplayServiceImpl extends ServiceImpl<ReplayMapper, Replay> implements IReplayService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public ResultVO findByQuestionId(String questionId) {
        if(StringUtils.isBlank(questionId)) {
            return ResultVOUtil.error("问题ID不能为空");
        }
        List<ReplayVO> list = baseMapper.findByQuestionId(questionId);
        return ResultVOUtil.success(list);
    }

    @Transactional
    @Override
    public ResultVO deleteById(String id) {
        if(StringUtils.isBlank(id)) {
            return ResultVOUtil.error("回答评论ID不能为空");
        }
        // 要删除的回答评论ID
        ArrayList<String> ids = new ArrayList<>();
        //先把要删除的一级回答id放入到集合中
        ids.add(id);
        //递归的子评论 id 加入到集合中
        this.getIds(ids, id);
        // 删除回答评论后，还要更新问题表中的回答数量，则先查问题id
        Replay replay = baseMapper.selectById(id);
        //批量删除集合中的id
        int size = baseMapper.deleteBatchIds(ids);
        if(size > 0) {
            // 更新问题表中的回答数量
            Question question = questionMapper.selectById(replay.getQuestionId());
            question.setReply(question.getReply() - size);
            questionMapper.updateById(question);
        }
        return ResultVOUtil.success();
    }
    @Transactional
    @Override
    public ResultVO add(Replay replay) {
        // 新增到回答表
        boolean ok = this.save(replay);       // 更新问题表中的回答数量
        if(ok) {
            // 更新问题表中的回答数量
            Question question = questionMapper.selectById(replay.getQuestionId());
            question.setReply(question.getReply() + 1);
            questionMapper.updateById(question);
        }
        return ResultVOUtil.success();
    }

    /**
     * 递归方法
     * @param ids
     * @param parentId
     */
    private void getIds(List<String> ids, String parentId) {
        //查询子评论的对象
        QueryWrapper<Replay> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", parentId);
        List<Replay> replayList = baseMapper.selectList(wrapper);
        if (CollectionUtils.isNotEmpty(replayList)) {
            for(Replay replay: replayList ) {
                String id = replay.getId();
                ids.add(id);
                // 递归继续查询子评论id
                this.getIds(ids, id);
            }
        }
    }
}
