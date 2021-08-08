package com.shoukailiang.community.system.feign;

import com.shoukailiang.community.entities.SysMenu;
import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.feign.IFeignSystemController;
import com.shoukailiang.community.system.service.ISysMenuService;
import com.shoukailiang.community.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignSystemController implements IFeignSystemController {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService sysMenuService;

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

    /**
     * 通过用户ID查询拥有权限
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<SysMenu> findMenuListByUserId(String userId) {
        return sysMenuService.findByUserId(userId);
    }

    /**
     * 查询所有讲师
     * @return
     */
    @Override
    public List<SysUser> findAllTeachers() {
        List<SysUser> list = sysUserService.findTeachersByRoleId();
        return list;
    }
}