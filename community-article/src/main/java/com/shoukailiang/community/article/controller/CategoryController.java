package com.shoukailiang.community.article.controller;

import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.article.service.ICategoryService;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "分类管理接口,提供分类的增删改查",description = "分类管理接口,提供分类的增删改查")
@RestController // 所有方法的返回值转换为 Json 字符串进行响应
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("询正常状态下的分类及分类下的所有标签")
    @GetMapping("/label/list")
    public ResultVO findCategoryAndLabel(){
        return categoryService.findCategoryAndLabel();
    }

}
