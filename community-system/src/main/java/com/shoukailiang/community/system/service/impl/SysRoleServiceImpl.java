package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.entities.SysRole;
import com.shoukailiang.community.system.mapper.SysRoleMapper;
import com.shoukailiang.community.system.req.SysRoleREQ;
import com.shoukailiang.community.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.Result;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public Result queryPage(SysRoleREQ req) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper();
        // 条件查询
        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.like("name", req.getName());
        }
        wrapper.orderByAsc("update_date");
        IPage<SysRole> sysRoleIPage = baseMapper.selectPage(req.getPage(), wrapper);
        return Result.ok(sysRoleIPage);
    }

    @Transactional
    @Override
    public Result deleteById(String id) {
        // 1. 通过角色 id 删除角色信息表数据
        baseMapper.deleteById(id);
        // 2. 通过角色 id 删除角色菜单关系表数据
        baseMapper.deleteRoleMenuByRoleId(id);
        return Result.ok();
    }

    @Override
    public Result findMenuIdsById(String id) {
        List<String> menuIdsById = baseMapper.findMenuIdsById(id);
        return Result.ok(menuIdsById);
    }

    @Transactional
    @Override
    public Result saveRoleMenu(String roleId, List<String> menuIds) {
        // 1. 先删除角色菜单关系表数据
        baseMapper.deleteRoleMenuByRoleId(roleId); // 2. 再保存新的角色菜单关系表数据
        if (CollectionUtils.isNotEmpty(menuIds)) {
            baseMapper.saveRoleMenu(roleId, menuIds);
        }
        return Result.ok();
    }
}
