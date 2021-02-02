package com.example.web.controller;

import com.jleo.jcontrol.access.Permission;
import com.jleo.jcontrol.bean.DO.RoleDO;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlProperties;
import com.jleo.jcontrol.role.dao.RoleDao;
import com.jleo.jcontrol.session.Conversation;
import com.jleo.jcontrol.session.security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController

public class Test {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private Conversation conversation;

    @RequestMapping("test")
    public String test(HttpServletRequest request, HttpServletResponse response) {
        List<RoleDO> list = roleDao.getAllRoleByUserId("123");
        //conversation.signIn(request, response, "2017110426", null);
        return "hello";
    }

    @Permission(user = "2017110426", role = "123", pass = false)
    @RequestMapping("test2")
    public CodeResult test2() {
        return new CodeResult("test2");
    }

}
