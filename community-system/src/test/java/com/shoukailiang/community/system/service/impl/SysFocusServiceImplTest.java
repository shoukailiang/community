package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shoukailiang.community.entities.SysFocus;
import com.shoukailiang.community.entities.SysUser;
import com.shoukailiang.community.system.mapper.SysFocusMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysFocusServiceImplTest {


    @Autowired
    private SysFocusMapper sysFocusMapper;

    @Test
    public void findFansList() {
        List<String> strings = sysFocusMapper.selectFans(String.valueOf(9));
        System.out.println(strings);
    }
}