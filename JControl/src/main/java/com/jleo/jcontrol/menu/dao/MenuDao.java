package com.jleo.jcontrol.menu.dao;

import com.jleo.jcontrol.bean.DO.MenuDO;
import com.jleo.jcontrol.bean.DO.RoleMenuDO;

import java.util.List;

/**
 * @author jleo
 * @date 2021/1/31
 */
public interface MenuDao {
    List<MenuDO> selectAllMenu();
    MenuDO selectMenuById(String id);
    RoleMenuDO selectRoleMenuByRole(String roleName);
    List<MenuDO> selectMenuByIds(String ids);
}
