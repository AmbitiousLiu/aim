package com.jleo.jcontrol.role.service;

import com.jleo.jcontrol.bean.DO.RoleDO;
import com.jleo.jcontrol.role.dao.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jleo
 * @date 2021/2/2
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    public List<RoleDO> getRoleByUserId(String userId) {
        return roleDao.getAllRoleByUserId(userId);
    }

}
