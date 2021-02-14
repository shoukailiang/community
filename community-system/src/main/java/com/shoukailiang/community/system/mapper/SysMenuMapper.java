package com.shoukailiang.community.system.mapper;

import com.shoukailiang.community.entities.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单信息表 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 查询指定用户ID的所拥有的权限（目录、菜单、按钮)
     * @param userId
     * @return
     */
    List<SysMenu> findByUserId(@Param("userId") String userId);
}
