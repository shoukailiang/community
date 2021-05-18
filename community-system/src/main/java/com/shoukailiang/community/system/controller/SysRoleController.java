package com.shoukailiang.community.system.controller;


import com.shoukailiang.community.entities.SysRole;
import com.shoukailiang.community.system.dto.SysRoleDTO;
import com.shoukailiang.community.system.req.SysRoleREQ;
import com.shoukailiang.community.system.service.ISysRoleService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
@Api(value = "角色管理接口", description = "角色管理接口，提供角色的增、删、改、查")
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private ISysRoleService sysRoleService;

    @ApiOperation("根据角色名称查询列表接口")
    @PostMapping("/search")
    public ResultVO search(@RequestBody SysRoleREQ req) {
        return sysRoleService.queryPage(req);
    }

    @ApiOperation("新增角色信息接口")
    @PostMapping
    public ResultVO save(@RequestBody SysRoleDTO sysRoleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO,sysRole);
        sysRoleService.save(sysRole);
        return ResultVOUtil.success();
    }

    @ApiOperation("查询角色详情接口")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @GetMapping("/{id}")
    public ResultVO view(@PathVariable("id") String id) {
        return ResultVOUtil.success(sysRoleService.getById(id));
    }

    @ApiOperation("修改角色信息接口")
    @PutMapping
    public ResultVO update(@RequestBody SysRoleDTO sysRoleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO,sysRole);
        sysRoleService.updateById(sysRole);
        return ResultVOUtil.success();
    }

    @ApiOperation("通过角色ID删除角色接口")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        return sysRoleService.deleteById(id);
    }


    @ApiOperation("根据角色id查询拥有的菜单ids接口")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true)
    @GetMapping("/{id}/menu/ids")
    public ResultVO findMenuIdsById(@PathVariable("id") String id) {
        return sysRoleService.findMenuIdsById(id);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true),
            @ApiImplicitParam(name = "menuIds", value = "菜单id集合", required = true, allowMultiple = true, dataType = "String")
    })
    @ApiOperation("新增角色菜单权限数据接口")
    @PostMapping("/{id}/menu/save")
    public ResultVO saveRoleMenu(@PathVariable("id") String id, @RequestBody List<String> menuIds) {
        return sysRoleService.saveRoleMenu(id, menuIds);
    }

}
