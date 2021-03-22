package com.example.web.VO;

/**
 * @author jleo
 * @date 2021/3/4
 */
public enum TaskType {
    SHELL(1),
    HTTP(2);

    Integer type;

    TaskType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public static String getByValue(Integer type) {
        for (TaskType value : TaskType.values()) {
            if (value.type.equals(type)) {
                return value.name();
            }
        }
        return null;
    }
}
