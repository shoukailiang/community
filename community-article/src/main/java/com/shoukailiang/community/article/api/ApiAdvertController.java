package com.shoukailiang.community.article.api;

import com.shoukailiang.community.article.service.IAdvertService;
import com.shoukailiang.community.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "广告管API理接口", description = "广告管理API接口，不需要通过身份认证就可以访问")
@RequestMapping("/api/advert")
@RestController
public class ApiAdvertController {
    @Autowired
    private IAdvertService advertService;

    @ApiOperation("查询指定广告位置下的所有广告信息接口")
    @ApiImplicitParam(name="position", value="广告位置编号", required=true)
    @GetMapping("/show/{position}") // /advert/show/{position}
    public Result show(@PathVariable("position") int position) {
        return advertService.findByPosition(position);
    }

}
