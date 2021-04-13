package com.shoukailiang.community.system.feign;

import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.feign.IFeignSystemController;
import com.shoukailiang.community.system.service.ISysRoleService;
import com.shoukailiang.community.system.service.ISysUserService;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignSystemController implements IFeignSystemController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysRoleService sysRoleService;

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return
     */
    @Override
    public SysUser findUserByUsername(String username) {
        return sysUserService.findByUsername(username);
    }

    @Override
    public String findRoleById(String id) {
        return sysRoleService.findRoleById(id);
    }

}