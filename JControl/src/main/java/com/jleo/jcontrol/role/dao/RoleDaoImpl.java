package com.jleo.jcontrol.role.dao;

import com.jleo.jcontrol.bean.DO.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

/**
 * @author jleo
 * @date 2021/1/31
 */
@Repository
public class RoleDaoImpl implements RoleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String selectRolesByUserId = "select * from user_role left join role on user_role.role_name = role.name where user_id = ?";

    @Override
    public List<RoleDO> getAllRoleByUserId(String userId) {
        return jdbcTemplate.query(selectRolesByUserId, new Object[]{userId}, (ResultSet resultSet, int i) -> {
            return new RoleDO(){{
                setName(resultSet.getString(2));
                setWhitelist(resultSet.getString(4));
                setBlacklist(resultSet.getString(5));
            }};
        });
    }
}
