package com.shoukailiang.community.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shoukailiang.community.entities.SysFocus;

import java.util.List;

/**
 * <p>
 * 关注 Mapper 接口
 * </p>
 *
 * @author shoukailiang
 * @since 2021-03-07
 */
public interface SysFocusMapper extends BaseMapper<SysFocus> {

    List<String> selectFans(String id);

    List<String> selectFocus(String id);
}
