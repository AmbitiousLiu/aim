package com.jleo.jcontrol.access;

import com.google.gson.Gson;
import com.jleo.jcontrol.bean.DO.RoleDO;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.role.dao.RoleDao;
import com.jleo.jcontrol.role.service.RoleService;
import com.jleo.jcontrol.session.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Autowired
    private RoleDao roleDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!conversation.isLogin(request)) {
            returnFalse(response, JControlConstant.CODE_RESULT_NOT_LOGIN, "not logged in yet");
            return false;
        }
        // other
        String userId = conversation.getUserId(request);
        List<RoleDO> allRole = roleDao.getAllRoleByUserId(userId);
        String servletPath = request.getServletPath();
        for (RoleDO roleDO : allRole) {
            if (!StringUtils.isEmpty(roleDO.getWhitelist())) {
                String[] whites = roleDO.getWhitelist().split(JControlConstant.MENU_SEPARATOR);
                for (String white : whites) {
                    Pattern p = Pattern.compile(white);
                    Matcher m = p.matcher(servletPath);
                    if(m.matches()){
                        return true;
                    }
                }
            } else {
                if (StringUtils.isEmpty(roleDO.getBlacklist())) {
                    return true;
                } else {
                    boolean access = true;
                    String[] blacks = roleDO.getBlacklist().split(JControlConstant.MENU_SEPARATOR);
                    for (String black : blacks) {
                        Pattern p = Pattern.compile(black);
                        Matcher m = p.matcher(servletPath);
                        if(m.matches()){
                            access = false;
                        }
                    }
                    if (access) {
                        return true;
                    }
                }
            }
        }
        returnFalse(response, JControlConstant.CODE_RESULT_ERROR, "no access at path:" + servletPath);
        return false;
    }

    private void returnFalse(HttpServletResponse response, int code, String message) throws Exception{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(gson.toJson(new CodeResult(code, JControlConstant.JCONTROL_CONSOLE_NAME + message)));
        } catch (IOException ignored) {
        }
    }
}
