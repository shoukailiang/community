package com.shoukailiang.community.system.controller;


import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.system.req.RegisterREQ;
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
