package com.example.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.web.DO.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author jleo
 * @date 2021/2/18
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    @Update("update menu set id = #{menu.id}, name = #{menu.name}, address = #{menu.address} where id = #{menuId}")
    int updateMenu(@Param("menuId") String menuId, @Param("menu") Menu menu);
}
