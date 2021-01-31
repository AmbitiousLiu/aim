package com.jleo.jcontrol.menu.service;

import com.jleo.jcontrol.bean.DO.MenuDO;
import com.jleo.jcontrol.bean.VO.MenuVO;

import java.util.List;
import java.util.Set;

/**
 * @author jleo
 * @date 2021/1/31
 */
public interface MenuService {
    List<MenuVO> getMenuByUser(String userId);
    List<MenuVO> spliceMenu(Set<MenuDO> menuDOSet);
}
