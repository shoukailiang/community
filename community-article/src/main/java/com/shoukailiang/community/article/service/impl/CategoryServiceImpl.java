package com.shoukailiang.community.article.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.article.mapper.CategoryMapper;
import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.article.service.ICategoryService;
import com.shoukailiang.community.article.vo.CategoryVO;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {
    @Override
    public ResultVO queryPage(CategoryREQ req) {
        // 查询条件对象
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        // 分类名称，状态
        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.like("name", req.getName());
        }
        if (req.getStatus() != null) {
            wrapper.eq("status", req.getStatus());
        }

        wrapper.orderByDesc("status").orderByAsc("sort");
        // 第一个参数page分页对象，第二个参数查询条件
        IPage<Category> data = baseMapper.selectPage(req.getPage(), wrapper);
        return ResultVOUtil.success(data);
    }

    @Override
    public boolean updateById(Category category) {
        return super.updateById(category);// 调用父类的更新
    }

    @Override
    public ResultVO findAllNormal() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("status",1);// 1是正常
        List<Category> categories = baseMapper.selectList(wrapper);
        return ResultVOUtil.success(categories);
    }

    @Override
    public ResultVO findCategoryAndLabel() {
        List<CategoryVO> categoryAndLabel = baseMapper.findCategoryAndLabel();
        return ResultVOUtil.success(categoryAndLabel);
    }

    @Override
    public CategoryVO getById(String id) {
        Category category = baseMapper.selectById(id);
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category,categoryVO);
        return categoryVO;
    }
}
