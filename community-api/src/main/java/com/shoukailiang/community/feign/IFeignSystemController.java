package com.shoukailiang.community.feign;

import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "system-server", path = "/system")
public interface IFeignSystemController {

    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @ApiOperation("Feign接口-通过用户名查询用户信息")
    @GetMapping("/api/feign/user/{username}")
    SysUser findUserByUsername(@PathVariable("username") String username);


    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    @ApiOperation("Feign接口-查询相关角色")
    @GetMapping("/api/feign/getRoleById/{id}")
    String findRoleById(@PathVariable("id") String id);
}
