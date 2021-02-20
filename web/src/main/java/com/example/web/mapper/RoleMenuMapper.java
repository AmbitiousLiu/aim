package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.DO.Menu;
import com.example.web.DO.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jleo
 * @date 2021/2/19
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Update("update role_menu set role_name = #{roleMenu.roleName}, have = #{roleMenu.have}, have_not = #{roleMenu.haveNot} where role_name = #{roleName}")
    int updateRoleMenu(@Param("roleName") String roleName, @Param("roleMenu") RoleMenu roleMenu);

    @Update("update role set name = #{roleMenu.roleName} where name = #{roleName}")
    int updateRole(@Param("roleName") String roleName, @Param("roleMenu") RoleMenu roleMenu);

    @Delete("delete from role_menu where role_name = #{roleName}")
    int deleteById(@Param("roleName") String roleName);
}
