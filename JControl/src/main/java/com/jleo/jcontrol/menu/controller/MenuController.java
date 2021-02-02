package com.jleo.jcontrol.menu.controller;

import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.menu.service.MenuService;
import com.jleo.jcontrol.session.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jleo
 * @date 2021/2/1
 */
@RestController
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private Conversation conversation;

    @RequestMapping("jcontrol/getMenu")
    public CodeResult getMenu(HttpServletRequest request) {
        String userId = conversation.getUserId(request);
        return new CodeResult(menuService.getMenuByUser(userId));
    }
}
