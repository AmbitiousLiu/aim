package com.jleo.jcontrol.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author jleo
 * @date 2021/1/19
 * @description used to control the session
 */
public interface Conversation {

    /**
     * determine whether the user is logged in
     * @param request
     * @return
     */
    boolean isLogin(HttpServletRequest request) throws InterruptedException;

    /**
     * do login
     * @param request
     * @param response
     * @return
     */
    boolean signIn(HttpServletRequest request, HttpServletResponse response, String userId, Map<String, String> userInfo);

    /**
     * do logout
     * @param request
     * @param response
     * @return
     */
    boolean signOut(HttpServletRequest request, HttpServletResponse response);

    <T> T getObjectFromToken(HttpServletRequest request, String name, Class<T> clazz);

    String getStringFromToken(HttpServletRequest request, String name);

    String getUserId(HttpServletRequest request);
}
