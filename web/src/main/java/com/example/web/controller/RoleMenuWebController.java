package com.example.web.controller;

import com.example.web.DO.RoleMenu;
import com.example.web.VO.RoleMenuVO;
import com.example.web.service.RoleMenuWebService;
import com.google.gson.Gson;
import com.jleo.jcontrol.access.Permission;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jleo
 * @date 2021/2/19
 */
@RestController
public class RoleMenuWebController {

    @Autowired
    RoleMenuWebService roleMenuWebService;

    @Autowired
    Gson gson;

    @Permission(role = "administrator")
    @RequestMapping("/getRoleMenu")
    CodeResult getRoleMenu() {
        try {
            return new CodeResult(roleMenuWebService.getRoleMenu());
        } catch (Exception e) {
            return new CodeResult(JControlConstant.CODE_RESULT_ERROR, e.getMessage());
        }
    }

    @Permission(role = "administrator")
    @RequestMapping("/addRoleMenu")
    CodeResult addRoleMenu(@RequestParam("roleMenu") String roleMenu) {
        RoleMenuVO roleMenu1 = gson.fromJson(roleMenu, RoleMenuVO.class);
        return roleMenuWebService.addRoleMenu(roleMenu1) ? new CodeResult() : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "添加失败");
    }

    @Permission(role = "administrator")
    @RequestMapping("/editRoleMenu")
    CodeResult editRoleMenu(String roleName, String roleMenu) {
        RoleMenuVO roleMenu1 = gson.fromJson(roleMenu, RoleMenuVO.class);
        return roleMenuWebService.editRoleMenu(roleName, roleMenu1) ? new CodeResult() : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "修改失败");
    }

    @Permission(role = "administrator")
    @RequestMapping("/deleteRoleMenu")
    CodeResult deleteRoleMenu(String roleName) {
        return roleMenuWebService.deleteRoleMenu(roleName) ? new CodeResult() : new CodeResult(JControlConstant.CODE_RESULT_ERROR, "删除失败");
    }

}
