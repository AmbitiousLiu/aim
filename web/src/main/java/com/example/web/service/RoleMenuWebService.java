package com.example.web.service;

import com.example.web.DO.Role;
import com.example.web.DO.RoleMenu;
import com.example.web.VO.RoleMenuVO;
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

    public List<RoleMenuVO> getRoleMenu() {
        return roleMenuMapper.selectAllRoleMenu();
    }

    @Transactional
    public boolean editRoleMenu(String roleName, RoleMenuVO roleMenuVO) {
        RoleMenu roleMenu = new RoleMenu(){{
            setRoleName(roleMenuVO.getName());
            setHave(roleMenuVO.getHave());
            setHaveNot(roleMenuVO.getHaveNot());
        }};
        Role role = new Role(){{
            setName(roleMenuVO.getName());
            setWhitelist(roleMenuVO.getWhitelist());
            setBlacklist(roleMenuVO.getBlacklist());
        }};
        int a = roleMenuMapper.updateRoleMenu(roleName, roleMenu);
        int b = roleMapper.updateRole(roleName, role);
        return a == 1 && b == 1;
    }

    @Transactional
    public boolean deleteRoleMenu(String roleName) {
        int a = roleMenuMapper.deleteById(roleName);
        int b = roleMapper.deleteOne(roleName);
        return a == 1 && b == 1;
    }

    @Transactional
    public boolean addRoleMenu(RoleMenuVO roleMenuVO) {
        RoleMenu roleMenu = new RoleMenu(){{
            setRoleName(roleMenuVO.getName());
            setHave(roleMenuVO.getHave());
            setHaveNot(roleMenuVO.getHaveNot());
        }};
        int a = roleMenuMapper.insert(roleMenu);
        Role role = new Role(){{
            setName(roleMenuVO.getName());
            setWhitelist(roleMenuVO.getWhitelist());
            setBlacklist(roleMenuVO.getBlacklist());
        }};
        int b = roleMapper.insert(role);
        return a == 1 && b == 1;
    }

}
