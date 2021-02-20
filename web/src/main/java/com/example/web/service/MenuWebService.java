package com.example.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.web.DO.Menu;
import com.example.web.mapper.MenuMapper;
import com.jleo.jcontrol.bean.DO.MenuDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jleo
 * @date 2021/2/18
 */
@Service
public class MenuWebService {

    @Autowired
    private MenuMapper menuMapper;

    public boolean updateMenu(String menuId, Menu menuDO) {
        return menuMapper.updateMenu(menuId, menuDO) == 1;
    }

    public boolean addMenu(Menu menu) {
        return menuMapper.insert(menu) == 1;
    }

    public boolean deleteMenu(String menuId) {
        return menuMapper.deleteById(menuId) == 1;
    }
}
