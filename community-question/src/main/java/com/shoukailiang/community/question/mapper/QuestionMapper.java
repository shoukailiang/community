package com.shoukailiang.community.question.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.entities.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

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

}
