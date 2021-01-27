package com.shoukailiang.community.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.Result;

/**
 * 文章分类表service
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 分页查询分类信息
     * @param req 查询条件
     * @return
     */
    Result queryPage(CategoryREQ req);
}
