package com.shoukailiang.community.article.api;

import com.shoukailiang.community.article.req.ArticleListREQ;
import com.shoukailiang.community.article.req.ArticleREQ;
import com.shoukailiang.community.article.req.SearchREQ;
import com.shoukailiang.community.article.service.IArticleService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "文章管理接口", description = "文章管理接口，不需要身份认证")
@RestController
@RequestMapping("/api/article")
public class ApiArticleController {
    @Autowired
    private IArticleService articleService;

    @ApiOperation("查询文章详情")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/{id}")
    public ResultVO view(@PathVariable("id") String id){
        return articleService.findArticleAndLabel(id);
    }

    @ApiImplicitParam(name = "id", value = "文章ID", required = true)
    @ApiOperation("更新浏览次数")
    @PutMapping("/viewCount/{id}")
    public ResultVO updateViewCount(@PathVariable("id") String id) {
        return articleService.updateViewCount(id);
    }

    @ApiOperation("公开且已审核的文章列表接口")
    @PostMapping("/list")
    public ResultVO list(@RequestBody ArticleListREQ req) {
        return articleService.findListByLabelIdOrCategoryId(req);
    }

    @ApiOperation("热门文章")
    @GetMapping("/hotlist")
    public ResultVO hotlist() {
        return articleService.findHotList();
    }

    @ApiOperation("前端文章搜索搜索")
    @GetMapping("/search/{title}/{current}/{size}")
    public ResultVO search(
            @PathVariable("title") String title,@PathVariable("current") Long current,@PathVariable("size") Long size){
        return articleService.queryPage(title,current,size);
    }
}
