package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.article.mapper.CategoryMapper;
import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.article.service.ICategoryService;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


    @Override
    public ResultVO findAllNormal() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("status",1);// 1是正常
        List<Category> categories = baseMapper.selectList(wrapper);
        return ResultVOUtil.success(categories);
    }

    @Override
    public ResultVO findCategoryAndLabel() {
        List<Category> categoryAndLabel = baseMapper.findCategoryAndLabel();
        return ResultVOUtil.success(categoryAndLabel);
    }
}
