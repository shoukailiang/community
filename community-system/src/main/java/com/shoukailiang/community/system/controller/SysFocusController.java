package com.shoukailiang.community.system.controller;


import com.shoukailiang.community.dto.FocusUser;
import com.shoukailiang.community.entities.SysFocus;
import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.system.service.impl.SysFocusServiceImpl;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 用户关注 前端控制器
 * </p>
 *
 * @author shoukailiang
 * @since 2021-03-07
 */
@Api(value = "用户关注接口", description = "用户关注接口")
@RestController
@RequestMapping("focus")
public class SysFocusController {

    @Autowired
    private SysFocusServiceImpl sysFocusService;

    @ApiOperation("查询已经关注的用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/{id}")
    public ResultVO findById(@PathVariable("id") String id) {
        return ResultVOUtil.success(sysFocusService.findById(id));
    }
    @ApiOperation("关注用户")
    @PostMapping
    public ResultVO focus(@RequestBody SysFocus sysFocus){
        sysFocusService.focus(sysFocus);
        return ResultVOUtil.success();
    }

    // 查询是否关注
    @ApiOperation("是否关注")
    @PostMapping("/isfocus")
    public ResultVO Isfocus(@RequestBody SysFocus sysFocus){
        boolean isfocus = sysFocusService.Isfocus(sysFocus);
        return ResultVOUtil.success(isfocus);
    }
    @ApiOperation("查询已关注个数")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/userId/{id}")
    public ResultVO findByUserIdNum(@PathVariable("id") String id) {
        return ResultVOUtil.success(sysFocusService.findByUserIdNum(id));
    }

    @ApiOperation("查询粉丝数个数")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/focusId/{id}")
    public ResultVO findByFocusNum(@PathVariable("id") String id) {
        return ResultVOUtil.success(sysFocusService.findByFocusNum(id));
    }

    @ApiOperation("查询我的粉丝列表")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/fans/{id}")
    public ResultVO findFansList(@PathVariable("id") String id) {
        List<FocusUser> FansList = sysFocusService.findFansList(id);
        return ResultVOUtil.success(FansList);
    }

    @ApiOperation("查询我的关注")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @GetMapping("/myfocus/{id}")
    public ResultVO findFocusList(@PathVariable("id") String id) {
        List<FocusUser> FansList = sysFocusService.findFocusList(id);
        return ResultVOUtil.success(FansList);
    }
}
