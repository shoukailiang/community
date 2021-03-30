package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.entities.SysFocus;
import com.shoukailiang.community.system.mapper.SysFocusMapper;
import com.shoukailiang.community.system.service.ISysFocusService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysFocusServiceImpl extends ServiceImpl<SysFocusMapper, SysFocus> implements ISysFocusService {

    @Override
    public List<SysFocus> findById(String userId) {
        QueryWrapper<SysFocus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public void focus(SysFocus sysFocus) {
        QueryWrapper<SysFocus> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",sysFocus.getUserId());
        wrapper.eq("focus_id",sysFocus.getFocusId());
        SysFocus sysFocus1 = baseMapper.selectOne(wrapper);
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
}
