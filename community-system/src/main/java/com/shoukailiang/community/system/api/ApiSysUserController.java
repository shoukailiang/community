package com.shoukailiang.community.system.api;


import com.shoukailiang.community.system.req.RegisterREQ;
import com.shoukailiang.community.system.service.ISysUserService;
import com.shoukailiang.community.util.base.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "用户管理接口", description = "用户管理接口,不用通过身份认证即可调用接口")
@RestController
@RequestMapping("/api/user")
public class ApiSysUserController {
    @Autowired
    private ISysUserService sysUserService;

    @ApiImplicitParam(name = "username", value = "用户名", required = true)
    @ApiOperation("校验用户名是否存在")
    @GetMapping("/username/{username}")
    public ResultVO checkUsername(@PathVariable("username") String username) {
        return sysUserService.checkUsername(username);
    }

    @ApiOperation("注册用户接口")
    @PostMapping("/register")
    public ResultVO register(@RequestBody RegisterREQ req) {
        return sysUserService.register(req);
    }
}
