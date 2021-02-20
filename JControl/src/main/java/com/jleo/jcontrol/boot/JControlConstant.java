package com.jleo.jcontrol.boot;

/**
 * @author jleo
 * @date 2021/1/25
 */
public class JControlConstant {
    // JControl
    public static String JCONTROL_CONSOLE_NAME = "[JControl]";

    // cookie
    public static String COOKIE_TOKEN_NAME = "token";

    public static int COOKIE_EXPIRATION = 60 * 60;

    // menu
    public static String MENU_SEPARATOR = ",";

    public static int MENU_MAX_LENGTH = 10;

    // controller
    public static int CODE_RESULT_SUCCESS = 0;

    public static String CODE_RESULT_SUCCESS_MESSAGE = "JControl成功返回";

    public static int CODE_RESULT_ERROR = -1;

    public static int CODE_RESULT_NOT_LOGIN = 1;

    public static String CODE_RESULT_ERROR_MESSAGE = "JControl返回错误";
}
