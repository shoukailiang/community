package com.shoukailiang.community.question.service;

import com.shoukailiang.community.entities.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.feign.req.UserInfoREQ;
import com.shoukailiang.community.question.dto.QuestionDTO;
import com.shoukailiang.community.question.req.QuestionUserREQ;
import com.shoukailiang.community.util.base.BaseRequest;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

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
    ResultVO findHotList(BaseRequest<Question> req);

    /**
     * 最近问答列表
     * @param req
     * @return
     */
    ResultVO findNewList(BaseRequest<Question> req);

    /**
     * 等待回答
     * @param req
     * @return
     */
    ResultVO findWaitList(BaseRequest<Question> req);

    /**
     * 据标签id查询问答列表
     * @param req
     * @param labelId
     * @return
     */
    ResultVO findListByLabelId(BaseRequest<Question> req, String labelId);

    /**
     * 根据问题ID查询问题详情与标签ids
     * @param id
     * @return
     */
    ResultVO findById(String id);

    /**
     * 更新浏览次数
     * @param id
     * @return
     */
    ResultVO updateViewCount(String id);

    /**
     * 修改或者新增文章数据
     * @param questionDTO
     * @return
     */
    ResultVO updateOrSave(QuestionDTO questionDTO);

    /**
     * 假删除，通过 问题id 修改状态为 0 ，表示已删
     * @param id
     * @return
     */
    public ResultVO deleteById(String id);


    /**
     * 更新点赞数
     * @param id
     * @param count
     * @return
     */
    ResultVO updateThumhup(String id, int count);

    /**
     * 根据用户id查询问题
     * @param req
     * @return
     */
    ResultVO findListByUserId(QuestionUserREQ req);

    /**
     * 查询提问总记录数
     * @return
     */
    ResultVO getQuestionTotal();

    /**
     * 更新问题与回答表中的用户信息
     * @param req
     * @return
     */
    boolean updateUserInfo(UserInfoREQ req);
}
