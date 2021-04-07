package com.shoukailiang.community.system.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.dto.FocusUser;
import com.shoukailiang.community.entities.SysFocus;
import com.shoukailiang.community.entities.SysUser;

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

    int findByUserIdNum(String id);

    int findByFocusNum(String id);

    List<FocusUser> findFansList(String id);

    List<FocusUser> findFocusList(String id);
}
