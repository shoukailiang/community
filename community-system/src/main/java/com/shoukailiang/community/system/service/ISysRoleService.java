package com.shoukailiang.community.system.service;

import com.shoukailiang.community.entities.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;


/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-04-13
 */
public interface ISysRoleService extends IService<SysRole> {
    String findRoleById(String id);
}
