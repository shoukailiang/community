package com.shoukailiang.community.system.service;

import com.shoukailiang.community.entities.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.system.req.RegisterREQ;
import com.shoukailiang.community.system.req.SysUserCheckPasswordREQ;
import com.shoukailiang.community.system.req.SysUserREQ;
import com.shoukailiang.community.system.req.SysUserUpdatePasswordREQ;
import com.shoukailiang.community.util.base.ResultVO;

import java.util.List;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 条件分页查询用户列表
     * @param req
     * @return
     */
    ResultVO queryPage(SysUserREQ req);

    /**
     * 根据用户id查询此用户拥有的角色ids
     * @param id
     * @return
     */
    ResultVO findRoleIdsById(String id);


    /**
     * 假删除，将 `is_enabled` 状态更新为 `0`
     * @param id
     * @return
     */
    ResultVO deleteById(String id);

    /**
     * 检验原密码是否正确
     * @param req
     * @return
     */
    ResultVO checkPassword(SysUserCheckPasswordREQ req);

    /**
     * 修改用户密码
     * @param req
     * @return
     */
    ResultVO updatePassword(SysUserUpdatePasswordREQ req);

    /**
     * 更新用户信息
     * @param sysUser
     * @return
     */
    ResultVO update(SysUser sysUser);

    /**
     * 统计总用户数
     * @return
     */
    ResultVO getUserTotal();

    /**
     * 检查用户是否存在
     * @param username
     * @return
     */
    ResultVO checkUsername(String username);

    /**
     * 注册
     * @param req
     * @return
     */
    ResultVO register(RegisterREQ req);

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    SysUser findByUsername(String username);


}
