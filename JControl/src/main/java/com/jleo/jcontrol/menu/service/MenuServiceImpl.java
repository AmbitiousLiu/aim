package com.jleo.jcontrol.menu.service;

import com.jleo.jcontrol.bean.DO.MenuDO;
import com.jleo.jcontrol.bean.DO.RoleDO;
import com.jleo.jcontrol.bean.DO.RoleMenuDO;
import com.jleo.jcontrol.bean.VO.MenuVO;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.menu.dao.MenuDao;
import com.jleo.jcontrol.role.dao.RoleDao;
import com.jleo.jcontrol.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author jleo
 * @date 2021/1/31
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<MenuVO> getMenuByUser(String userId) {
        // get roles of this user
        List<RoleDO> roleDOList = roleService.getRoleByUserId(userId);

        // get all menu of these roles have
        // if 'have' == null -> get all : get 'have'
        // if 'haveNot' == null -> return : remove 'haveNot'
        // deduplication
        // splice
        Set<MenuDO> menuDOSet = new HashSet<>();
        for (RoleDO roleDO : roleDOList) {
            RoleMenuDO roleMenuDO = menuDao.selectRoleMenuByRole(roleDO.getName());
            if (roleMenuDO == null) {
                return null;
            }
            if (StringUtils.isEmpty(roleMenuDO.getHave())) {
                if (StringUtils.isEmpty(roleMenuDO.getHaveNot())) {
                    menuDOSet.addAll(menuDao.selectAllMenu());
                } else {
                    menuDOSet.addAll(menuDao.selectAllMenu().stream().filter(menuDO -> {
                        List<String> haveNotStrings = Arrays.stream(roleMenuDO.getHaveNot().split(JControlConstant.MENU_SEPARATOR)).collect(Collectors.toList());
                        return !haveNotStrings.contains(menuDO.getId());
                    }).collect(Collectors.toList()));
                }
            } else {
                menuDOSet.addAll(menuDao.selectMenuByIds(roleMenuDO.getHave()));
            }
        }
        return spliceMenu(menuDOSet);
    }

    @Override
    public List<MenuVO> spliceMenu(Set<MenuDO> menuDOSet) {
        List<MenuVO> result = new ArrayList<>();
        Map<String, MenuVO> menuVOMap = new HashMap<>();
        int i = 0;
        while (menuVOMap.size() < menuDOSet.size()) {
            i++;
            if (i > JControlConstant.MENU_MAX_LENGTH) {
                break;
            }
            int finalI = i;
            List<MenuDO> list = menuDOSet.stream().filter(menuDO -> menuDO.getId().length() == finalI).sorted((a, b) -> {
                return a.getId().compareTo(b.getId());
            }).collect(Collectors.toList());
            for (MenuDO menuDO : list) {
                MenuVO menuVO = new MenuVO(){{
                    setId(menuDO.getId());
                    setName(menuDO.getName());
                    setAddress(menuDO.getAddress());
                }};
                if (i == 1) {
                    result.add(menuVO);
                    menuVOMap.put(menuVO.getId(), menuVO);
                } else {
                    String prefix = menuDO.getId().substring(0, menuDO.getId().length() - 1);
                    if (menuVOMap.get(prefix) != null) {
                        if (menuVOMap.get(prefix).getChild() == null) {
                            menuVOMap.get(prefix).setChild(new ArrayList<>());
                        }
                        menuVOMap.get(prefix).getChild().add(menuVO);
                        menuVOMap.put(menuVO.getId(), menuVO);
                    }
                }
            }
        }
        return result;
    }
}
