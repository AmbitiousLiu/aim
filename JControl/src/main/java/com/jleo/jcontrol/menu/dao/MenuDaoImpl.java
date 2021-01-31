package com.jleo.jcontrol.menu.dao;

import com.jleo.jcontrol.bean.DO.MenuDO;
import com.jleo.jcontrol.bean.DO.RoleMenuDO;
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
public class MenuDaoImpl implements MenuDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String selectAllMenu = "select * from menu";
    private String selectMenuById = "select * from menu where id = ?";
    private String selectRoleMenuByRole = "select * from role_menu where role_name = ?";
    private String selectMenuByIds = "select * from menu where id in (?)";

    @Override
    public List<MenuDO> selectAllMenu() {
        return jdbcTemplate.query(selectAllMenu, (ResultSet resultSet, int i) -> {
            return new MenuDO(){{
                setId(resultSet.getString(1));
                setName(resultSet.getString(2));
                setAddress(resultSet.getString(3));
            }};
        });
    }

    @Override
    public MenuDO selectMenuById(String id) {
        return jdbcTemplate.query(selectMenuById, new Object[]{id}, (ResultSet resultSet) -> {
            return new MenuDO(){{
                setId(resultSet.getString(1));
                setName(resultSet.getString(2));
                setAddress(resultSet.getString(3));
            }};
        });
    }

    @Override
    public RoleMenuDO selectRoleMenuByRole(String roleName) {
        return jdbcTemplate.query(selectRoleMenuByRole, new Object[]{roleName}, (ResultSet resultSet) -> {
            return new RoleMenuDO(){{
                setRoleName(resultSet.getString(1));
                setHave(resultSet.getString(2));
                setHaveNot(resultSet.getString(3));
            }};
        });
    }

    @Override
    public List<MenuDO> selectMenuByIds(String ids) {
        return jdbcTemplate.query(selectMenuByIds, new Object[]{ids}, (ResultSet resultSet, int i) -> {
            return new MenuDO(){{
                setId(resultSet.getString(1));
                setName(resultSet.getString(2));
                setAddress(resultSet.getString(3));
            }};
        });
    }
}
