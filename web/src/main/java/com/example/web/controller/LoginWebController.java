package com.example.web.controller;

import com.example.web.service.LoginService;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.session.Conversation;
import com.jleo.jcontrol.session.security.AESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @author jleo
 * @date 2021/2/16
 */
@RestController
public class LoginWebController {

    @Autowired
    LoginService loginService;

    @Autowired
    Conversation conversation;

    @RequestMapping("/login")
    public CodeResult doLogin(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if (loginService.doLogin(userName, password)) {
            conversation.signIn(request, response, userName, null);
            return new CodeResult();
        }
        return new CodeResult(JControlConstant.CODE_RESULT_ERROR, "登录失败");
    }

    @RequestMapping("/getUserName")
    public CodeResult getUserName(HttpServletRequest request) {
        return new CodeResult(loginService.getUserName(conversation.getUserId(request)));
    }

    @RequestMapping("/logout")
    public CodeResult logout(HttpServletRequest request, HttpServletResponse response) {
        return new CodeResult(conversation.signOut(request, response));
    }
}
