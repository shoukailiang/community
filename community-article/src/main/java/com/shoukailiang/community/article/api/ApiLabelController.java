package com.shoukailiang.community.article.api;

import com.shoukailiang.community.article.service.ILabelService;
import com.shoukailiang.community.entities.Label;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "公开标签管理接口", description = "查询热门标签，")
@RestController
@RequestMapping("/api/label")
public class ApiLabelController {

    @Autowired
    private ILabelService iLabelService;

    @ApiOperation("热门标签")
    @GetMapping("/hottag")
    public ResultVO hottag() {
        List<Label> hotTag = iLabelService.findHotTag();
        return ResultVOUtil.success(hotTag);
    }

}
