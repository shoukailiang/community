package com.shoukailiang.community.system.mapper;

import com.shoukailiang.community.entities.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shoukailiang.community.entities.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 通过角色id删除角色菜单关系表数据
     * @param roleId
     * @return
     */
    boolean deleteRoleMenuByRoleId(@Param("roleId") String roleId);

    /**
     * 根据角色id查询此角色拥有的权限菜单 ids
     * @param id
     * @return
     */
    List<String> findMenuIdsById(@Param("id") String id);

    /**
     * 新增角色菜单权限数据到 sys_role_menu
     * @param roleId 角色id
     * @param menuIds 菜单id集合
     * @return
     */
    boolean saveRoleMenu(@Param("roleId") String roleId, @Param("menuIds") List<String> menuIds);

    /**
     * 根据roleID查找所有的老师
     * @param roleId
     * @return
     */
    // TODO 将来准备优化
    List<SysUser> findTeachersByRoleId(@Param("roleId") String roleId);
}
