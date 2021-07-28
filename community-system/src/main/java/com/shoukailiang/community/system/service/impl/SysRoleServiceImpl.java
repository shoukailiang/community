package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.entities.SysRole;
import com.shoukailiang.community.system.mapper.SysRoleMapper;
import com.shoukailiang.community.system.req.SysRoleREQ;
import com.shoukailiang.community.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.ResultEnum;
import com.shoukailiang.community.util.exception.CommunityException;
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
    public ResultVO queryPage(SysRoleREQ req) {
        QueryWrapper<SysRole> wrapper = new QueryWrapper();
        // 条件查询
        if (StringUtils.isNotEmpty(req.getName())) {
            wrapper.like("name", req.getName());
        }
        wrapper.orderByAsc("gmt_modified");
        IPage<SysRole> sysRoleIPage = baseMapper.selectPage(req.getPage(), wrapper);
        return ResultVOUtil.success(sysRoleIPage);
    }

    @Transactional
    @Override
    public ResultVO deleteById(String id) {
        // 1. 通过角色 id 删除角色信息表数据
        baseMapper.deleteById(id);
        // 2. 通过角色 id 删除角色菜单关系表数据
        baseMapper.deleteRoleMenuByRoleId(id);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO findMenuIdsById(String id) {
        List<String> menuIdsById = baseMapper.findMenuIdsById(id);
        return ResultVOUtil.success(menuIdsById);
    }

    @Transactional
    @Override
    public ResultVO saveRoleMenu(String roleId, List<String> menuIds) {
        SysRole sysRole = baseMapper.selectById(roleId);
        if(sysRole==null){
            throw new CommunityException(ResultEnum.NOT_ROLE.getCode(),ResultEnum.NOT_ROLE.getMessage());
        }
        // 1. 先删除角色菜单关系表数据
        baseMapper.deleteRoleMenuByRoleId(roleId); // 2. 再保存新的角色菜单关系表数据
        if (CollectionUtils.isNotEmpty(menuIds)) {
            baseMapper.saveRoleMenu(roleId, menuIds);
        }
        return ResultVOUtil.success();
    }
}
