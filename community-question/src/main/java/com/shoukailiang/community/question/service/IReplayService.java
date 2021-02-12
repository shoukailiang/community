package com.shoukailiang.community.question.service;

import com.shoukailiang.community.entities.Replay;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

/**
 * <p>
 * 回答信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
public interface IReplayService extends IService<Replay> {
    /**
     * 通过问题id递归查询所有评论
     * @param questionId
     * @return
     */
    ResultVO findByQuestionId(String questionId);

    /**
     * 通过问题id递归删除所有评论
     * @param id
     * @return
     */
    ResultVO deleteById(String id);

    /**
     * 新增回答并更新回答数量
     * @param replay
     * @return
     */
    ResultVO add(Replay replay);

}
