package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.dto.FocusUser;
import com.shoukailiang.community.entities.SysFocus;
import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.system.mapper.SysFocusMapper;
import com.shoukailiang.community.system.mapper.SysUserMapper;
import com.shoukailiang.community.system.service.ISysFocusService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SysFocusServiceImpl extends ServiceImpl<SysFocusMapper, SysFocus> implements ISysFocusService {

    @Override
    public List<SysFocus> findById(String userId) {
        QueryWrapper<SysFocus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 2 不能关注自己，1关注成功，0取消关注
     * @param sysFocus
     * @return
     */
    @Override
    public void focus(SysFocus sysFocus) {
        QueryWrapper<SysFocus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",sysFocus.getUserId());
        wrapper.eq("focus_id",sysFocus.getFocusId());
        SysFocus sysFocus1 = baseMapper.selectOne(wrapper);
        if(sysFocus.getFocusId().equals(sysFocus.getUserId())){
            return ;
        }
        if(sysFocus1==null){
            super.save(sysFocus);
        }else {
            baseMapper.delete(wrapper);
        }
    }

    @Override
    public boolean Isfocus(SysFocus sysFocus) {
        QueryWrapper<SysFocus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",sysFocus.getUserId());
        wrapper.eq("focus_id",sysFocus.getFocusId());
        if(baseMapper.selectOne(wrapper)!=null){
            return true;
        }
        return false;
    }

    @Override
    public int findByUserIdNum(String id) {
        QueryWrapper<SysFocus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",id);
        Integer integer = baseMapper.selectCount(wrapper);
        return integer;
    }

    @Override
    public int findByFocusNum(String id) {
        QueryWrapper<SysFocus> wrapper = new QueryWrapper<>();
        wrapper.eq("focus_id",id);
        Integer integer = baseMapper.selectCount(wrapper);
        return integer;
    }

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<FocusUser> findFansList(String id) {

        List<String> fans= baseMapper.selectFans(id);
        if(CollectionUtils.isEmpty(fans)){
            return null;
        }
        List<SysUser> sysUsers = sysUserMapper.selectBatchIds(fans);
        List<FocusUser> users = sysUsers.stream().map(e->
                new FocusUser(e.getId(),e.getUsername(),e.getNickName(),e.getImageUrl(),e.getMobile(),e.getEmail())
        ).collect(Collectors.toList());
        return users;
    }

    @Override
    public List<FocusUser> findFocusList(String id) {
        List<String> focus= baseMapper.selectFocus(id);
        if(CollectionUtils.isEmpty(focus)){
            return null;
        }
        List<SysUser> sysUsers = sysUserMapper.selectBatchIds(focus);
        List<FocusUser> users = sysUsers.stream().map(e->
            new FocusUser(e.getId(),e.getUsername(),e.getNickName(),e.getImageUrl(),e.getMobile(),e.getEmail())
        ).collect(Collectors.toList());
        return users;
    }
}
