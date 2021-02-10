package com.shoukailiang.community.question.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.entities.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 问题信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
public interface QuestionMapper extends BaseMapper<Question> {

    /**
     * 根据标签id查询问答列表
     * @param page
     * @param labelId
     * @return
     */
    IPage<Question> findListByLabelId(IPage<Question> page, @Param("labelId") String labelId );

    /**
     * 	根据问题ID查询问题详情与标签ids
     * @param id
     * @return
     */
    Question findQuestionAndLabelIdsById(String id);

    /**
     *  通过问题 id 删除问题标签中间表
     * @param questionId
     * @return
     */
    boolean deleteQuestionLabel(@Param("questionId") String questionId);

    /**
     *  新增问题标签中间表数据
     * @param questionId
     * @param labelIds
     * @return
     */
    boolean saveQuestionLabel(@Param("questionId") String questionId,
                              @Param("labelIds") List<String> labelIds);

}
