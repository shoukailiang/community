package com.shoukailiang.community.question.mapper;

import com.shoukailiang.community.entities.Replay;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shoukailiang.community.question.vo.ReplayVO;

import java.util.List;

/**
 * <p>
 * 回答信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
public interface ReplayMapper extends BaseMapper<Replay> {
    /**
     * 通过问题id递归查询所有评论
     * @param questionId
     * @return
     */
    List<ReplayVO> findByQuestionId(String questionId);
}
