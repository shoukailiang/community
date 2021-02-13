package com.shoukailiang.community.system.controller;


import com.shoukailiang.community.entities.SysMenu;
import com.shoukailiang.community.system.req.SysMenuREQ;
import com.shoukailiang.community.system.service.ISysMenuService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 * 菜单信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */

@Api(value = "菜单管理接口", description = "菜单管理接口，提供菜单的增、删、改、查")
@RestController
@RequestMapping("/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService sysMenuService;

    @ApiOperation("根据菜单名称查询列表接口")
    @PostMapping("/search")
    public ResultVO search(@RequestBody SysMenuREQ req) {
        return sysMenuService.queryList(req);
    }

    @ApiOperation("通过菜单ID删除菜单接口")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true)
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        sysMenuService.deleteById(id);
        return ResultVOUtil.success();
    }


    @ApiOperation("新增菜单信息接口")
    @PostMapping // 请求地址 /menu
    public ResultVO save(@RequestBody SysMenu sysMenu) {
        sysMenuService.save(sysMenu);
        return ResultVOUtil.success();
    }


    @ApiOperation("查询菜单详情接口")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true)
    @GetMapping("/{id}")
    public ResultVO view(@PathVariable("id") String id) {
        return ResultVOUtil.success(sysMenuService.getById(id));
    }

    @ApiOperation("修改菜单信息接口")
    @PutMapping // 请求地址 /menu
    public ResultVO update(@RequestBody SysMenu sysMenu) {
        sysMenu.setUpdateDate(new Date());
        sysMenuService.updateById(sysMenu);
        return ResultVOUtil.success();
    }

}
