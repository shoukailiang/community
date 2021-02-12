package com.shoukailiang.community.article.api;

import com.shoukailiang.community.article.service.ICategoryService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这些接口不需要身份认证就可以访问到
 */
@Api(value = "分类管理api接口",description = "分类管理api接口，不需要身份认证")
@RestController
@RequestMapping("/api/category")
public class ApiCategoryController {
    @Autowired
    private ICategoryService categoryService;

    @ApiOperation("查询所有正常状态的分类的公开接口")
    @GetMapping("/list")
    public ResultVO list(){
        return categoryService.findAllNormal();
    }

    @ApiOperation("询正常状态下的分类及分类下的所有标签的公开接口")
    @GetMapping("/label/list")
    public ResultVO findCategoryAndLabel(){
        return categoryService.findCategoryAndLabel();
    }
}
