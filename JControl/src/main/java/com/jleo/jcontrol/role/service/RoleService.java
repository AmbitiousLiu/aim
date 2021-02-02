package com.jleo.jcontrol.role.service;

import com.jleo.jcontrol.bean.DO.RoleDO;

import java.util.List;

/**
 * @author jleo
 * @date 2021/2/2
 */
public interface RoleService {
    List<RoleDO> getRoleByUserId(String userId);
}
