package com.shoukailiang.community.article.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.article.vo.ArticleVO;
import com.shoukailiang.community.entities.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shoukailiang.community.feign.req.UserInfoREQ;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
    ArticleVO findArticleAndLabelById(String id);





    /**
     * 通过分类id或标签id查询文章列表
     * @param page
     * @param req
     * @return
     */
    IPage<Article> findListByLabelIdOrCategoryId(IPage<Article> page, @Param("req") ArticleListREQ req);

    /**
     * 统计各分类下的文章数（将分类所属标签的文章整合在一起）
     * @return
     */
    List<Map<String, Object>> selectCategoryTotal();

    /**
     * 统计近6个月的文章数
     * @return
     */
    List<Map<String, Object>> selectMonthAritcleTotal();

    /**
     * 更新文章和评论的用户信息
     * @param req
     * @return
     */
    boolean updateUserInfo(UserInfoREQ req);
}
