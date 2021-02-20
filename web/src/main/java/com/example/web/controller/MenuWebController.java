package com.example.web.controller;

import com.example.web.DO.Menu;
import com.example.web.service.MenuWebService;
import com.google.gson.Gson;
import com.jleo.jcontrol.access.Permission;
import com.jleo.jcontrol.bean.DO.MenuDO;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jleo
 * @date 2021/2/18
 */
@RestController
public class MenuWebController {

    @Autowired
    private MenuWebService menuWebService;
    @Autowired
    private Gson gson;

    @Permission(role = "administrator")
    @RequestMapping("/editMenu")
    public CodeResult editMenu(@RequestParam("menuId") String menuId,
                               @RequestParam("menu") String menu) {
        Menu menuDO = gson.fromJson(menu, Menu.class);
        return menuWebService.updateMenu(menuId, menuDO) ? new CodeResult() : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "菜单修改失败");
    }

    @Permission(role = "administrator")
    @RequestMapping("/addMenu")
    public CodeResult addMenu(@Param("menu") String menu) {
        Menu menuDO = gson.fromJson(menu, Menu.class);
        return menuWebService.addMenu(menuDO) ? new CodeResult() : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "添加失败");
    }

    @Permission(role = "administrator")
    @RequestMapping("/deleteMenu")
    public CodeResult deleteMenu(String menuId) {
        return menuWebService.deleteMenu(menuId) ? new CodeResult() : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "删除失败");
    }


}
