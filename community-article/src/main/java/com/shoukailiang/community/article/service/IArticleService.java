package com.shoukailiang.community.article.service;

import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.article.req.ArticleREQ;
import com.shoukailiang.community.article.req.ArticleUserREQ;
import com.shoukailiang.community.entities.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.Result;
import com.shoukailiang.community.util.enums.ArticleStatusEnum;

/**
 * <p>
 * 文章信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-06
 */
public interface IArticleService extends IService<Article> {
    /**
     * 条件分页查询文章列表
     * @param req
     * @return
     */
    Result queryPage(ArticleREQ req);

    /**
     *  通过文章id查询文章详情与标签
     * @param id
     * @return
     */
    Result findArticleAndLabel(String id);

    /**
     * 修改或新增
     * @param article
     * @return
     */
    Result updateOrSave(Article article);

    /**
     * 修改文章状态
     * @param id
     * @param statusEnum
     * @return
     */
    Result updateStatus(String id, ArticleStatusEnum statusEnum);

    /**
     * 根据用户id查询公开或为公开的问文章列表
     * @param req
     * @return
     */
    Result findListByUserId(ArticleUserREQ req);

    /**
     * 更新点赞数
     * @param id
     * @param count
     * @return
     */
    Result updateThumhup(String id, int count);

    /**
     * 更新浏览次数
     * @param id
     * @return
     */
    Result updateViewCount(String id);

    /**
     * 公开且审核通过的文章列表
     * @param req
     * @return
     */
    Result findListByLabelIdOrCategoryId(ArticleListREQ req);

}
