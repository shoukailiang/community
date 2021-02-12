package com.shoukailiang.community.article.controller;


import com.shoukailiang.community.article.service.ICommentService;
import com.shoukailiang.community.entities.Comment;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 评论信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-07
 */
@Api(value = "评论管理API接口", description = "评论管理API接口")
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @ApiImplicitParam(name="id", value="评论ID", required=true)
    @ApiOperation("删除评论接口")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        return commentService.deleteById(id);
    }

    @ApiOperation("新增评论信息接口")
    @PostMapping
    public ResultVO save(@RequestBody Comment comment) {
        commentService.save(comment);
        return ResultVOUtil.success();
    }
}
