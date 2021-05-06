package com.shoukailiang.community.api;

import com.shoukailiang.community.entities.Question;
import com.shoukailiang.community.question.req.QuestionByLabelIdREQ;
import com.shoukailiang.community.question.service.IQuestionService;
import com.shoukailiang.community.util.base.BaseRequest;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "问答管理API接口", description = "问答管理API接口，不需要通过身份认证即可调用接口")
@RestController
@RequestMapping("/api/question")
public class ApiQuestionController {
    @Autowired
    private IQuestionService questionService;

    @ApiOperation("分页查询热门问答列表接口")
    @PostMapping("/hot")
    public ResultVO findHostList(@RequestBody BaseRequest<Question> req) {
        return questionService.findHotList(req);
    }

    @ApiOperation("分页查询最新问答列表")
    @PostMapping("/new")
    public ResultVO findNewList(@RequestBody BaseRequest<Question> page) {
        return questionService.findNewList(page);
    }

    @ApiOperation("分页查询等待回答列表")
    @PostMapping("/wait")
    public ResultVO findWaitList(@RequestBody BaseRequest<Question> page) {
        return questionService.findWaitList(page);
    }

    @ApiOperation("根据标签ID分页查询问答列表接口")
    @PostMapping("/list")
    public ResultVO findListByLabelId(@RequestBody QuestionByLabelIdREQ req) {
        return questionService.findListByLabelId(req, req.getLabelId());
    }

    @ApiOperation("查询问题详情接口")
    @ApiImplicitParam(name = "id", value = "问题ID", required = true)
    @GetMapping("/{id}")
    public ResultVO view(@PathVariable("id") String id) {
        return questionService.findById(id);
    }

    @ApiImplicitParam(name = "id", value = "问题ID", required = true)
    @ApiOperation("更新浏览次数")
    @PutMapping("/viewCount/{id}")
    public ResultVO updateViewCount(@PathVariable("id") String id) {
        return questionService.updateViewCount(id);
    }
}
