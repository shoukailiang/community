package com.shoukailiang.community.system.service;

import com.shoukailiang.community.entities.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.system.req.SysRoleREQ;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 条件查询角色列表
     * @param req
     * @return
     */
    ResultVO queryPage(SysRoleREQ req);

    /**
     * 1. 通过id删除角色信息表数据
     * 2. 通过id删除角色权限关系表数据
     * @param id
     * @return
     */
    ResultVO deleteById(String id);

    /**
     * 根据角色id查询此角色拥有的权限菜单 ids
     * @param id  角色id
     * @return
     */
    ResultVO findMenuIdsById(String id);

    /**
     * 新增角色菜单权限数据到 sys_role_menu
     * @param roleId
     * @param menuIds
     * @return
     */
    ResultVO saveRoleMenu(String roleId, List<String> menuIds);

}
