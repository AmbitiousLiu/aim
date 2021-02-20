package com.jleo.jcontrol.access;

import com.google.gson.Gson;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.role.service.RoleService;
import com.jleo.jcontrol.session.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author jleo
 * @date 2021/1/17
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private Conversation conversation;

    @Autowired
    private Gson gson;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 前后端分离的验证
        if (!conversation.isLogin(request)) {
            returnFalse(response, "not logged in yet");
            return false;
        }
        // other
        return true;
    }

    private void returnFalse(HttpServletResponse response, String message) throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(gson.toJson(new CodeResult(JControlConstant.CODE_RESULT_NOT_LOGIN, JControlConstant.JCONTROL_CONSOLE_NAME + message)));
        } catch (IOException ignored) {
        }
    }
}
