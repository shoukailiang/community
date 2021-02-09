package com.shoukailiang.community.question.service;

import com.shoukailiang.community.entities.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.BaseRequest;
import com.shoukailiang.community.util.base.Result;

/**
 * <p>
 * 问题信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
public interface IQuestionService extends IService<Question> {
    /**
     * 查询热门回答列表
     * @param req
     * @return
     */
    Result findHotList(BaseRequest<Question> req);

    /**
     * 最近问答列表
     * @param req
     * @return
     */
    Result findNewList(BaseRequest<Question> req);

    /**
     * 等待回答
     * @param req
     * @return
     */
    Result findWaitList(BaseRequest<Question> req);

    /**
     * 据标签id查询问答列表
     * @param req
     * @param labelId
     * @return
     */
    Result findListByLabelId(BaseRequest<Question> req, String labelId);

}
