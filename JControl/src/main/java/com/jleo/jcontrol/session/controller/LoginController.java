package com.jleo.jcontrol.session.controller;

import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.session.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jleo
 * @date 2021/2/16
 */
@RestController
public class LoginController {

    @Autowired
    private Conversation conversation;

    @RequestMapping("jcontrol/isLogin")
    public CodeResult isLogin(HttpServletRequest request, HttpServletResponse response) throws InterruptedException {
        if (conversation.isLogin(request)) {
            return new CodeResult();
        }
        return new CodeResult(JControlConstant.CODE_RESULT_ERROR, "未登录");
    }
}
