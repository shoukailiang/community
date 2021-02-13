package com.shoukailiang.community.system.mapper;

import com.shoukailiang.community.entities.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 根据用户id查询此用户拥有的角色ids
     * @param id 用户id
     * @return
     */
    List<String> findRoleIdsById(@Param("id") String id);

    /**
     * 通过用户 id 删除用户角色关系表数据
     * @param userId
     * @return
     */
    boolean deleteUserRoleByUserId(@Param("userId") String userId);

    /**
     * 新增用户角色关系数据到 sys_user_role
     * @param userId
     * @param roleIds
     * @return
     */
    boolean saveUserRole(@Param("userId") String userId, @Param("roleIds") List<String> roleIds);

}
