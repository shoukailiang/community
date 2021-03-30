package com.shoukailiang.community.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.entities.SysFocus;

import java.util.List;

/**
 * <p>
 * 关注 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-03-07
 */
public interface ISysFocusService extends IService<SysFocus> {
    List<SysFocus> findById(String UserId);

    void focus(SysFocus sysFocus);

    boolean Isfocus(SysFocus sysFocus);
}
