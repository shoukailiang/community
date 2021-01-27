package com.shoukailiang.community.article.controller;

import com.shoukailiang.community.article.req.CategoryREQ;
import com.shoukailiang.community.article.service.ICategoryService;
import com.shoukailiang.community.util.base.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/search")
    public Result search(@RequestBody CategoryREQ req){
        return categoryService.queryPage(req);
    }
}
