package com.shoukailiang.community.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

/**
 * 文章分类表service
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 查询正常状态的分类
     * @return
     */
    ResultVO findAllNormal();

    /**
     * 查询正常状态下的分类及分类下的所有标签
     * @return
     */
    ResultVO findCategoryAndLabel();
}
