package com.example.web.VO;

/**
 * @author jleo
 * @date 2021/3/8
 */
public enum AlarmType {

    letter(1, "站内信"),
    email(2, "邮件");

    Integer id;
    String name;

    AlarmType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getNameById(Integer id) {
        for (AlarmType value : AlarmType.values()) {
            if (value.id.equals(id)) {
                return value.name;
            }
        }
        return null;
    }
}
