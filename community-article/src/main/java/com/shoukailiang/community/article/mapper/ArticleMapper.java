package com.shoukailiang.community.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.article.req.ArticleREQ;
import com.shoukailiang.community.entities.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shoukailiang.community.util.base.Result;
import com.shoukailiang.community.util.enums.ArticleStatusEnum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 文章信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-06
 */
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 根据文章id查询文章详情与标签
     * @param id
     * @return
     */
    Article findArticleAndLabelById(String id);

    /**
     * 通过文章id删除标签（中间表）。@Param 在xml配置文件中直接应用这个别名，理论上一个参数不用加，但为了统一，都加上了
     *
     * @param articleId
     * @return
     */
    boolean deleteArticleLabel(@Param("articleId") String articleId);

    /**
     * 新增文章的标签（中间表）
     * @param articleId
     * @param labelIds
     * @return
     */
    boolean saveArticleLabel(@Param("articleId") String articleId,@Param("labelIds") List<String> labelIds);

    /**
     * 通过分类id或标签id查询文章列表
     * @param page
     * @param req
     * @return
     */
    IPage<Article> findListByLabelIdOrCategoryId(IPage<Article> page, @Param("req") ArticleListREQ req);

}
