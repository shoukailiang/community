package com.shoukailiang.community.system.service;

import com.shoukailiang.community.entities.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shoukailiang.community.system.req.SysMenuREQ;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;

import java.util.List;

/**
 * <p>
 * 菜单信息表 服务类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 条件查询菜单接口
     * @param req
     * @return
     */
    ResultVO queryList(SysMenuREQ req);

    /**
     * 通过菜单资源id删除权限数据
     * @param id 菜单资源id
     * @return
     */
    ResultVO deleteById(String id);

    /**
     * 通过用户id查询拥有的权限菜单树
     * @param userId
     * @return
     */
    ResultVO findUserMenuTree(String userId);

    /**
     * 通过用户id查询拥有权限
     * @param userId
     * @return
     */
    List<SysMenu> findByUserId(String userId);
}
