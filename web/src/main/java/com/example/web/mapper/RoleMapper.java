package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.DO.Role;
import com.example.web.DO.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author jleo
 * @date 2021/2/19
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Update("update role set name = #{role.name}, whitelist = #{role.whitelist}, blacklist = #{role.blacklist} where name = #{roleName}")
    int updateRole(@Param("roleName") String roleName, @Param("role") Role role);

    @Delete("delete from role where name = #{name}")
    int deleteOne(String name);
}
