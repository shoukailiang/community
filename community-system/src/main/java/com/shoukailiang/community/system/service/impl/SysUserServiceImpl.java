package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.shoukailiang.community.entities.SysMenu;
import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.feign.IFeignArticleController;
import com.shoukailiang.community.feign.IFeignQuestionController;
import com.shoukailiang.community.feign.req.UserInfoREQ;
import com.shoukailiang.community.system.dto.SysUserDTO;
import com.shoukailiang.community.system.mapper.SysRoleMapper;
import com.shoukailiang.community.system.mapper.SysUserMapper;
import com.shoukailiang.community.system.req.RegisterREQ;
import com.shoukailiang.community.system.req.SysUserCheckPasswordREQ;
import com.shoukailiang.community.system.req.SysUserREQ;
import com.shoukailiang.community.system.req.SysUserUpdatePasswordREQ;
import com.shoukailiang.community.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import com.shoukailiang.community.util.enums.ResultEnum;
import com.shoukailiang.community.util.exception.CommunityException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public ResultVO queryPage(SysUserREQ req) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        // 条件查询
        if (StringUtils.isNotEmpty(req.getUsername())) {
            wrapper.like("username", req.getUsername());
        }
        if (StringUtils.isNotEmpty(req.getMobile())) {
            wrapper.like("mobile", req.getMobile());
        }
        wrapper.orderByDesc("gmt_modified");
        IPage<SysUser> data = baseMapper.selectPage(req.getPage(), wrapper);
        return ResultVOUtil.success(data);
    }

    @Override
    public ResultVO findRoleIdsById(String id) {
        return ResultVOUtil.success(baseMapper.findRoleIdsById(id));
    }

    @Transactional
    @Override
    public ResultVO saveUserRole(String userId, List<String> roleIds) {
        SysUser sysUser = baseMapper.selectById(userId);
        if (sysUser == null) {
            throw new CommunityException(ResultEnum.NOT_USER.getCode(), ResultEnum.NOT_USER.getMessage());
        }
        // 1. 先删除用户角色关系表数据
        baseMapper.deleteUserRoleByUserId(userId); // 2. 再保存新的用户角色关系数据
        if (CollectionUtils.isNotEmpty(roleIds)) {
            baseMapper.saveUserRole(userId, roleIds);
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO deleteById(String id) {
        // 将 `is_enabled` 状态更新为 0 表示删除
        SysUser sysUser = baseMapper.selectById(id);
        if (sysUser == null) {
            return ResultVOUtil.error("用户不存在，删除失败");
        }
        sysUser.setIsEnabled(0);
        baseMapper.updateById(sysUser);
        return ResultVOUtil.success();
    }


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultVO checkPassword(SysUserCheckPasswordREQ req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return ResultVOUtil.error("用户ID不能为空,请重试");
        }
        if (StringUtils.isEmpty(req.getOldPassword())) {
            return ResultVOUtil.error("原密码不能为空,请重试");
        }
        SysUser sysUser = baseMapper.selectById(req.getUserId());
        if (sysUser == null) {
            return ResultVOUtil.error("用户不存在,请重试");
        }
        if (!passwordEncoder.matches(req.getOldPassword(), sysUser.getPassword())) {
            return ResultVOUtil.error("原密码输入错误");
        }
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO updatePassword(SysUserUpdatePasswordREQ req) {
        if (StringUtils.isEmpty(req.getUserId())) {
            return ResultVOUtil.error("用户ID不能为空,请重试");
        }
        if (StringUtils.isEmpty(req.getNewPassword())) {
            return ResultVOUtil.error("新密码不能为空,请重试");
        }
        if (StringUtils.isEmpty(req.getRepPassword())) {
            return ResultVOUtil.error("确认密码不能为空,请重试");
        }
        if (!req.getNewPassword().equals(req.getRepPassword())) {
            return ResultVOUtil.error("密码不一致,请重试");
        }
        SysUser sysUser = baseMapper.selectById(req.getUserId());
        if (sysUser == null) {
            return ResultVOUtil.error("用户不存在,请重试");
        }
        // 旧密码
        if (StringUtils.isNotEmpty(req.getOldPassword())) {
            if (!passwordEncoder.matches(req.getOldPassword(), sysUser.getPassword())) {
                return ResultVOUtil.error("原密码输入错误");
            }
        }
        // 新密码加密
        sysUser.setPassword(passwordEncoder.encode(req.getNewPassword()));
        sysUser.setPwdUpdateDate(LocalDateTime.now());
        baseMapper.updateById(sysUser);
        return ResultVOUtil.success();
    }


    @Autowired
    private IFeignArticleController feignArticleController;

    @Autowired
    private IFeignQuestionController feignQuestionController;

    @Transactional
    @Override
    public ResultVO update(SysUserDTO sysUserDTO) {
        // 1. 查询原用户信息
        SysUser user = baseMapper.selectById(sysUserDTO.getId());
        if (user == null) {
            return ResultVOUtil.error("更新的用户不存在");
        }
        // 2. 判断更新的信息中昵称和头像是否被改变
        if (!StringUtils.equals(sysUserDTO.getNickName(), user.getNickName())
                || !StringUtils.equals(sysUserDTO.getImageUrl(), user.getImageUrl())) {
            // 其中一个不相等，则更新用户信息
            // 2.1 调用文章微服务接口更新用户信息
            UserInfoREQ req = new UserInfoREQ(sysUserDTO.getId(), sysUserDTO.getNickName(), sysUserDTO.getImageUrl());
            feignArticleController.updateUserInfo(req);
            // 2.2 调用问答微服务接口更新用户信息
            feignQuestionController.updateUserInfo(req);
        }
        // 3. 更新用户信息表
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserDTO,sysUser);
        baseMapper.updateById(sysUser);
        return ResultVOUtil.success();
    }

    @Override
    public ResultVO getUserTotal() {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();     // 帐户是否可用(1 可用，0 删除用户)
        wrapper.eq("is_enabled", 1);
        Integer total = baseMapper.selectCount(wrapper);
        return ResultVOUtil.success(total);
    }

    @Override
    public ResultVO checkUsername(String username) {
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        SysUser sysUser = baseMapper.selectOne(wrapper);
        // 查询到则存在，存在 data=true 已被注册，不存在 data=false 未被注册
        return ResultVOUtil.success(sysUser == null ? false : true);
    }

    @Override
    public ResultVO register(RegisterREQ req) {
        if (StringUtils.isEmpty(req.getUsername())) {
            return ResultVOUtil.error("用户名不能为空，请重试");
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            return ResultVOUtil.error("密码不能为空，请重试");
        }
//        log.debug(String.valueOf(req));
        if (StringUtils.isEmpty(req.getRepPassword())) {
            return ResultVOUtil.error("确认密码不能为空，请重试");
        }
        if (!StringUtils.equals(req.getPassword(), req.getRepPassword())) {
            return ResultVOUtil.error("两次输入的密码不一致");
        }
        ResultVO resultVO = this.checkUsername(req.getUsername());
        if ((Boolean) resultVO.getData()) {
            return ResultVOUtil.error("用户已被注册，请更换个用户名");
        }
        // 校验都通过，新增用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUsername(req.getUsername());
        // 默认昵称和用户名一样
        sysUser.setNickName(req.getUsername());
        sysUser.setPassword(passwordEncoder.encode(req.getPassword()));
        // 提交用户信息
        this.save(sysUser);
        return ResultVOUtil.success();
    }

    @Override
    public SysUser findByUsername(String username) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        return baseMapper.selectOne(queryWrapper);
    }

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysUser> findTeachersByRoleId() {
        List<SysUser> teachersByRoleId = sysRoleMapper.findTeachersByRoleId("1422181437532053506");
        return teachersByRoleId;
    }


}
