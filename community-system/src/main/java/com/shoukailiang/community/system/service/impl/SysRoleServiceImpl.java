package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.entities.SysRole;
import com.shoukailiang.community.system.mapper.SysRoleMapper;
import com.shoukailiang.community.system.service.ISysRoleService;
import com.shoukailiang.community.util.enums.ResultEnum;
import com.shoukailiang.community.util.exception.CommunityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-04-13
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public String findRoleById(String id) {
        String roleId = baseMapper.findRoleId(id);
        //log.error("skl {}", roleId);
        if(StringUtils.isNotEmpty(roleId)){
            return roleId;
        }
        throw new CommunityException(ResultEnum.PERMISSION_NO);
    }
}
