package com.shoukailiang.community.article.service;

import com.shoukailiang.community.entities.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

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
    ResultVO findByArticleId(String articleId);

    /**
     * 通过评论id递归删除
     * @param id
     * @return
     */
    ResultVO deleteById(String id);

}
