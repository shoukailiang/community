package com.shoukailiang.community.article.controller;


import com.shoukailiang.community.article.req.AdvertREQ;
import com.shoukailiang.community.article.service.IAdvertService;
import com.shoukailiang.community.entities.Advert;
import com.shoukailiang.community.util.base.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 广告信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-08
 */
@Api(value = "广告管理接口", description = "广告管理接口增删改查")
@RestController
@RequestMapping("/advert")
public class AdvertController {

    @Autowired
    private IAdvertService advertService;

    @ApiOperation("根据广告标题与状态查询广告分页列表接口")
    @PostMapping("/search")
    public Result search(@RequestBody AdvertREQ req) {
        return advertService.queryPage(req);
    }

    @ApiImplicitParam(name="id", value="广告ID", required=true)
    @ApiOperation("删除广告接口")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") String id) {
        return advertService.deleteById(id);
    }


    @ApiImplicitParam(name = "id", value = "广告ID", required = true)
    @ApiOperation("查询广告详情接口")
    @GetMapping("/{id}")
    public Result view(@PathVariable("id") String id) {
        Advert byId = advertService.getById(id);
        return Result.ok(byId);
    }

    @ApiOperation("修改广告信息接口")
    @PutMapping
    public Result update(@RequestBody Advert advert) {
        advert.setUpdateDate(new Date());
        advertService.updateById(advert);
        return Result.ok();
    }

    @ApiOperation("新增广告信息接口")
    @PostMapping
    public Result save(@RequestBody Advert advert) {
        advertService.save(advert);
        return Result.ok();
    }
}
