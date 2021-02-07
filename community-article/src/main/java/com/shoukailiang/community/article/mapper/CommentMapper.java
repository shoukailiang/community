package com.shoukailiang.community.article.mapper;

import com.shoukailiang.community.entities.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 评论信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-07
 */
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 通过文章id查找评论
     * @param articleId
     * @return
     */
    List<Comment> findByArticleId(@Param("articleId") String articleId);
}
