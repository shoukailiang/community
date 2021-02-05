package com.shoukailiang.community.article.controller;

import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.article.service.ICategoryService;
import com.shoukailiang.community.entities.Category;
import com.shoukailiang.community.util.base.Result;
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

    /**
     * 	分页查询，@RequestBody 请求体中的json数据
     * @param req  分类名与状态查询和分页参数
     * @return 会将对象转换成json
     */
    @ApiOperation("根据分类名称与状态查询分类接口")
    @PostMapping("/search")
    public Result search(@RequestBody CategoryREQ req){
        return categoryService.queryPage(req);
    }

    @ApiOperation("根据类别详情接口")
    @ApiImplicitParam(name = "id",value = "类别id",required = true)
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id){
        Category category = categoryService.getById(id);
        return Result.ok(category);
    }
    @ApiOperation("修改类别详情接口")
    @PutMapping   //category
    public Result update(@RequestBody Category category){
        categoryService.updateById(category);
        return Result.ok();
    }
    @ApiOperation("新增类别详情接口")
    @PostMapping //category
    public Result save(@RequestBody Category category){
        categoryService.save(category);
        return Result.ok();
    }

    @ApiOperation("删除类别详情接口")
    @ApiImplicitParam(name = "id",value = "类别id",required = true)
    @DeleteMapping("/{id}")//category/1
    public Result delete(@PathVariable("id") String id){
        categoryService.removeById(id);
        return Result.ok();
    }

    @ApiOperation("查询所有正常状态的分类接口")
    @GetMapping("/list")
    public Result list(){
        return categoryService.findAllNormal();
    }

    @ApiOperation("询正常状态下的分类及分类下的所有标签")
    @GetMapping("/label/list")
    public Result findCategoryAndLabel(){
        return categoryService.findCategoryAndLabel();
    }

}
