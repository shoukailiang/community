package com.shoukailiang.community.system.controller;


import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.system.req.SysUserCheckPasswordREQ;
import com.shoukailiang.community.system.req.SysUserREQ;
import com.shoukailiang.community.system.req.SysUserUpdatePasswordREQ;
import com.shoukailiang.community.system.service.ISysUserService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
@Api(value = "用户管理接口", description = "用户管理接口,提供用户的增、删、改、查")
@RestController
@RequestMapping("user")
public class SysUserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ISysUserService sysUserService;

    @PostMapping("/search")
    public ResultVO search(@RequestBody SysUserREQ req) {
        return sysUserService.queryPage(req);
    }

    @ApiImplicitParam(name = "id", value = "用户Id", required = true)
    @ApiOperation("根据用户id查询所拥有的角色ids接口")
    @GetMapping("/{id}/role/ids")
    public ResultVO findRoleIdsById(@PathVariable("id") String id) {
        return sysUserService.findRoleIdsById(id);
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户Id", required = true),
            @ApiImplicitParam(name = "roleIds", value = "角色Id集合", required = true, allowMultiple = true, dataType = "String"),
    })
    @ApiOperation("新增用户角色关系数据接口")
    @PostMapping("/{id}/role/save") // /user/9/role/save
    public ResultVO saveUserRole(@PathVariable("id") String id, @RequestBody List<String> roleIds) {
        return sysUserService.saveUserRole(id, roleIds);
    }

    @ApiOperation("通过用户ID删除用户接口")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable("id") String id) {
        return sysUserService.deleteById(id);
    }


    @ApiOperation("新增用户信息接口")
    @PostMapping // 请求地址 /user
    public ResultVO save(@RequestBody SysUser sysUser) {
        // 密码加密处理
        String password = passwordEncoder.encode(sysUser.getPassword());
        sysUser.setPassword(password);
        // 新增
        sysUserService.save(sysUser);
        return ResultVOUtil.success();
    }

    @ApiOperation("查询用户详情接口")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @GetMapping("/{id}")
    public ResultVO view(@PathVariable("id") String id) {
        return ResultVOUtil.success(sysUserService.getById(id));
    }

    @ApiOperation("校验原密码接口")
    @PostMapping("/check/password")
    public ResultVO checkPassword(@RequestBody SysUserCheckPasswordREQ req) {
        return sysUserService.checkPassword(req);
    }

    @ApiOperation("更新用户密码接口")
    @PutMapping("/password")
    public ResultVO updatePassword(@RequestBody SysUserUpdatePasswordREQ req) {
        return sysUserService.updatePassword(req);
    }

    @ApiOperation("更新用户信息接口")
    @PutMapping
    public ResultVO update(@RequestBody SysUser sysUser) {
        return sysUserService.update(sysUser);
    }


    @ApiOperation("统计总用户数接口")
    @GetMapping("/total")
    public ResultVO userTotal() {
        return sysUserService.getUserTotal();
    }
}
