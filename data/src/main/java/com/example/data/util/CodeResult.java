package com.example.data.util;

/**
 * @author jleo
 * @date 2021/2/1
 */
public class CodeResult {
    private Integer code;
    private Object message;

    public CodeResult(Integer code, Object message) {
        this.code = code;
        this.message = message;
    }

    public CodeResult(Object message) {
        this(0, message);
    }

    public CodeResult() {
        this(0, Constant.DATA_LABEL + Constant.SUCCESS);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }
}
