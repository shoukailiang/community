package com.shoukailiang.community.system.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.shoukailiang.community.entities.SysMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/5/18 15:31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysMenuVO extends SysMenu {

    /**
     * 子菜单集合
     */
    private List<SysMenu> children;
}
