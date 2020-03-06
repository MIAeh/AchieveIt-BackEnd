package com.achieveit.application.domain;

import com.achieveit.application.enums.ErrorCode;

import java.io.Serializable;

/**
 * @Title: Result
 * @description: 自定义的Http请求返回的结果
 * @Author: Felix
 * @Date: 5/31/2018 14:22
 * @version: 1.0
 **/

public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 错误码. */
    private Integer errorCode;

    /** 具体的内容. */
    private Object data;

    public Result() {
    }

    public Result(ErrorCode errorCode, Object data) {
        this.errorCode = errorCode.getCode();
        this.data = data;
    }

    public Result(ErrorCode errorCode) {
        this.errorCode = errorCode.getCode();
        this.data = null;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
