package com.jleo.jcontrol.bean.VO;

import com.jleo.jcontrol.boot.JControlConstant;

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
        this(JControlConstant.CODE_RESULT_SUCCESS, message);
    }

    public CodeResult() {
        this(JControlConstant.CODE_RESULT_SUCCESS, JControlConstant.CODE_RESULT_SUCCESS_MESSAGE);
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
