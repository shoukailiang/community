package com.shoukailiang.community.article.controller;


import com.shoukailiang.community.article.req.ArticleREQ;
import com.shoukailiang.community.article.req.ArticleUserREQ;
import com.shoukailiang.community.article.service.IArticleService;
import com.shoukailiang.community.entities.Article;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.ArticleStatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 文章信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-06
 */
@Api(value = "文章管理接口", description = "文章管理接口，提供文章的增、删、改、查")
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @ApiOperation("根据文章标题与状态查询文章分页列表接口")
    @PostMapping("/search")
    public ResultVO search(@RequestBody ArticleREQ req){
        return articleService.queryPage(req);
    }


    @ApiOperation("查询文章详情")
    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @GetMapping("/{id}")
    public ResultVO view(@PathVariable("id") String id){
        return articleService.findArticleAndLabel(id);
    }

    @ApiOperation("修改文章信息接口")
    @PutMapping
    public ResultVO update(@RequestBody Article article) {
        return articleService.updateOrSave(article);
    }

    @ApiOperation("新增文章信息接口")
    @PostMapping
    public ResultVO save(@RequestBody Article article) {
        return articleService.updateOrSave(article);
    }

    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @ApiOperation("删除文章接口")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.DELETE);
    }

    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @ApiOperation("审核通过文章接口")
    @GetMapping("/audit/success/{id}")
    public ResultVO success(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.SUCCESS);
    }

    @ApiImplicitParam(name = "id",value = "文章id",required = true)
    @ApiOperation("审核不通过文章接口")
    @GetMapping("/audit/fail/{id}")
    public ResultVO fail(@PathVariable("id") String id) {
        return articleService.updateStatus(id, ArticleStatusEnum.FAIL);
    }


    @ApiOperation("根据用户ID查询公开或未公开的文章列表")
    @PostMapping("/user")
    public ResultVO findListByUserId(@RequestBody ArticleUserREQ req) {
        return articleService.findListByUserId(req);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="文章ID", required=true),
            @ApiImplicitParam(name="count", value="点赞数", required=true),
    })
    @ApiOperation("更新点赞数")
    @PutMapping("/thumb/{id}/{count}")
    public ResultVO updateThumhup(@PathVariable("id") String id, @PathVariable("count") int count) {
        return articleService.updateThumhup(id, count);
    }


    @ApiOperation("统计审核通过且公开的文章总记录数")
    @GetMapping("/total")
    public ResultVO getArticleTotal() {
        return articleService.getArticleTotal();
    }

    @ApiOperation("统计各分类下的文章数")
    @GetMapping("/category/total")
    public ResultVO categoryTotal() {
        return articleService.selectCategoryTotal();
    }

    @ApiOperation("查询近6个月发布的文章数")
    @GetMapping("/month/total")
    public ResultVO monthArticleTotal() {
        return articleService.selectMonthArticleTotal();
    }
}
