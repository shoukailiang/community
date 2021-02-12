package com.shoukailiang.community.article.service;

import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.article.req.ArticleREQ;
import com.shoukailiang.community.article.req.ArticleUserREQ;
import com.shoukailiang.community.entities.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
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
    ResultVO queryPage(ArticleREQ req);

    /**
     *  通过文章id查询文章详情与标签
     * @param id
     * @return
     */
    ResultVO findArticleAndLabel(String id);

    /**
     * 修改或新增
     * @param article
     * @return
     */
    ResultVO updateOrSave(Article article);

    /**
     * 修改文章状态
     * @param id
     * @param statusEnum
     * @return
     */
    ResultVO updateStatus(String id, ArticleStatusEnum statusEnum);

    /**
     * 根据用户id查询公开或为公开的问文章列表
     * @param req
     * @return
     */
    ResultVO findListByUserId(ArticleUserREQ req);

    /**
     * 更新点赞数
     * @param id
     * @param count
     * @return
     */
    ResultVO updateThumhup(String id, int count);

    /**
     * 更新浏览次数
     * @param id
     * @return
     */
    ResultVO updateViewCount(String id);

    /**
     * 公开且审核通过的文章列表
     * @param req
     * @return
     */
    ResultVO findListByLabelIdOrCategoryId(ArticleListREQ req);

    /**
     * 查询文章总记录数
     * @return
     */
    ResultVO getArticleTotal();

    /**
     * 统计每个分类写下的文章数
     * @return
     */
    ResultVO selectCategoryTotal();

    /**
     * 统计近6个月的文章数
     * @return
     */
    ResultVO selectMonthArticleTotal();


}
