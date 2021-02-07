package com.shoukailiang.community.article.service;

import com.shoukailiang.community.entities.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.Result;

/**
 * <p>
 * 评论信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-07
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 通过文章id查询所有评论
     * @param articleId
     * @return
     */
    Result findByArticleId(String articleId);

    /**
     * 通过评论id递归删除
     * @param id
     * @return
     */
    Result deleteById(String id);

}
