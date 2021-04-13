package com.shoukailiang.community.system.controller;


import com.shoukailiang.community.system.service.impl.SysRoleServiceImpl;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.ResultEnum;
import com.shoukailiang.community.util.exception.CommunityException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-04-13
 */
@Api(value = "角色管理接口", description = "角色管理接口，提供角色的增、删、改、查")
@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleServiceImpl sysRoleService;

    @ApiOperation("查询相关角色")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/{id}")
    public ResultVO findRoleById(@PathVariable("id") String id) {
        String role =null;
        try {
             role = sysRoleService.findRoleById(id);
        }catch (CommunityException e){
            return ResultVOUtil.build(e.getCode(),e.getMessage());
        }
        return ResultVOUtil.success(role);
    }
}
