package com.shoukailiang.community.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.article.vo.CategoryVO;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

/**
 * 文章分类表service
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 分页查询分类信息
     * @param req 查询条件
     * @return
     */
    ResultVO queryPage(CategoryREQ req);

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


    CategoryVO getById(String id);
}
