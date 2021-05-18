package com.shoukailiang.community.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shoukailiang.community.article.vo.CategoryVO;
import com.shoukailiang.community.entities.Category;

import java.util.List;

/**
 * 文章分类表的mapper接口
 */
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 查询正常状态下的分类及分类下的所有标签
     * @return
     */
    List<CategoryVO> findCategoryAndLabel();

}
