package com.example.web.controller;

import com.jleo.jcontrol.access.Permission;
import com.jleo.jcontrol.bean.DO.RoleDO;
import com.jleo.jcontrol.boot.JControlProperties;
import com.jleo.jcontrol.role.dao.RoleDao;
import com.jleo.jcontrol.session.security.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class Test {

    @Autowired
    private RoleDao roleDao;

    @Permission
    @RequestMapping("test")
    public String test() {
        List<RoleDO> list = roleDao.getAllRoleByUserId("2017110426");
        return "hello";
    }
}
