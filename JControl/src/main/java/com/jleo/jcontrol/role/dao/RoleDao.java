package com.jleo.jcontrol.role.dao;

import com.jleo.jcontrol.bean.DO.RoleDO;

import java.util.List;

/**
 * @author jleo
 * @date 2021/1/31
 */
public interface RoleDao {
    List<RoleDO> getAllRoleByUserId(String userId);
}
