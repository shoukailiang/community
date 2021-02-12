package com.shoukailiang.community.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shoukailiang.community.entities.SysMenu;
import com.shoukailiang.community.system.mapper.SysMenuMapper;
import com.shoukailiang.community.system.req.SysMenuREQ;
import com.shoukailiang.community.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shoukailiang.community.util.base.ResultVO;
import com.shoukailiang.community.util.base.ResultVOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 菜单信息表 服务实现类
 * </p>
 *
 * @author shoukailiang
 * @since 2021-02-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public ResultVO queryList(SysMenuREQ req) {
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(req.getName())){
            wrapper.like("name",req.getName());
        }
        // sort 升序
        wrapper.orderByAsc("sort").orderByDesc("update_date");
        // 获取所有菜单
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        // 封装树状菜单并响应
        List<SysMenu> data = this.buildTree(menuList);
        return ResultVOUtil.success(data);
    }



    /**
     * 将菜单封装成树状结构
     * @param menuList 所有菜单（目录，菜单，按钮）
     * @return
     */
    private List<SysMenu> buildTree(List<SysMenu> menuList) {
        // 1. 获取根菜单
        List<SysMenu> rootMenuList = new ArrayList<>();
        for(SysMenu menu: menuList) {
            // 如果 m.parentId 等于 0 就是根菜单
            if(menu.getParentId().equals( "0" )) {
                rootMenuList.add(menu);
            }
        }
        // 2. 根菜单下的子菜单
        for(SysMenu menu: rootMenuList) {
            childrenMenu(menuList, menu);
        }
        // 3. 返回根菜单，因为根菜单中封装了子菜单
        return rootMenuList;
    }

    /**
     * pid=0,id=1 系统管理》pid=1,id=2 用户管理》pid=2 增、删、改、查
     * pid=0,id=1 系统管理》pid=1,id=3 角色管理》pid=3 增、删、改、查
     * 递归获取子菜单，因为子菜单下还会有子菜单
     * @param menuList 所有菜单（包括目录、菜单、按钮）
     * @param menu 父菜单
     * @return
     */
    private SysMenu childrenMenu(List<SysMenu> menuList, SysMenu menu){
        // 封装菜单的 parentId = id 子菜单集合
        List<SysMenu> children = new ArrayList<>();
        // 每次都迭代所有菜单，判断是否为 menu 的子菜单
        for(SysMenu m: menuList) {
            // 如果 m.parentId 等于 id 则就是它的子菜单
            if( m.getParentId().equals( menu.getId() )) {
                // 是子菜单，则递归去找这个菜单的子菜单
                final SysMenu e = childrenMenu(menuList, m);
                children.add(e);
            }
        }
        // 封装 menu 的子菜单
        menu.setChildren(children); // 每有子菜单时返回
        return menu;
    }

    @Transactional
    @Override
    public ResultVO deleteById(String id) {
        // TODO 如果菜单已经被引用了，那么不能直接删掉，比如菜单已经给了角色了
        // 删除当前资源
        baseMapper.deleteById(id);
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        // TODO SysMenu::getChildren 只会删除子菜单，不会删除子菜单的子菜单
        wrapper.eq(SysMenu::getParentId,id);
        baseMapper.delete(wrapper);
        return ResultVOUtil.success();
    }

}
