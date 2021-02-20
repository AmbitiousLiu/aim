package com.example.web.service;

import com.example.web.DO.Role;
import com.example.web.DO.RoleMenu;
import com.example.web.mapper.RoleMapper;
import com.example.web.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author jleo
 * @date 2021/2/19
 */
@Service
public class RoleMenuWebService {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Autowired
    RoleMapper roleMapper;

    public List<RoleMenu> getRoleMenu() {
        return roleMenuMapper.selectList(null);
    }

    @Transactional
    public boolean editRoleMenu(String roleName, RoleMenu roleMenu) {
        int a = roleMenuMapper.updateRoleMenu(roleName, roleMenu);
        if (roleName.equals(roleMenu.getRoleName())) {
            return a == 1;
        }
        int b = roleMenuMapper.updateRole(roleName, roleMenu);
        return a == 1 && b == 1;
    }

    public boolean deleteRoleMenu(String roleName) {
        return roleMenuMapper.deleteById(roleName) == 1;
    }

    @Transactional
    public boolean addRoleMenu(RoleMenu roleMenu) {
        int a = roleMenuMapper.insert(roleMenu);
        Role role = new Role(){{
            setName(roleMenu.getRoleName());
        }};
        int b = roleMapper.insert(role);
        return a == 1 && b == 1;
    }

}
