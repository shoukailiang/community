package com.shoukailiang.community.question.controller;


import com.shoukailiang.community.entities.Question;
import com.shoukailiang.community.question.req.QuestionUserREQ;
import com.shoukailiang.community.question.service.IQuestionService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 问题信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Api(value = "问题管理接口", description = "问题管理接口，提供回答的增、删、改、查")
@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private IQuestionService questionService;


    @ApiOperation("新增问题信息接口")
    @PostMapping
    public ResultVO save(@RequestBody Question question) {
        ResultVO result = questionService.updateOrSave(question);
        return result;
    }

    @ApiOperation("修改问题信息接口")
    @PutMapping
    public ResultVO update(@RequestBody Question question) {
        ResultVO result = questionService.updateOrSave(question);
        return result;
    }

    @ApiImplicitParam(name="id", value="问题ID", required=true)
    @ApiOperation("删除问题接口")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        return questionService.deleteById(id);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="问题ID", required=true),
            @ApiImplicitParam(name="count", value="点赞数", required=true),
    })
    @ApiOperation("更新点赞数")
    @PutMapping("/thumb/{id}/{count}")
    public ResultVO updateThumhup(@PathVariable("id") String id, @PathVariable("count") int count) {
        return questionService.updateThumhup(id, count);
    }


    @ApiOperation("根据用户ID查询问题列表")
    @PostMapping("/user")
    public ResultVO findListByUserId(@RequestBody QuestionUserREQ req) {
        return questionService.findListByUserId(req);
    }

    @ApiOperation("查询提问总记录数")
    @GetMapping("/total")
    public ResultVO questionTotal() {
        return questionService.getQuestionTotal();
    }


}
