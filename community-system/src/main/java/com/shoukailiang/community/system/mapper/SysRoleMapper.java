package com.shoukailiang.community.system.mapper;

import com.shoukailiang.community.entities.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-04-13
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    String findRoleId(String id);
}
