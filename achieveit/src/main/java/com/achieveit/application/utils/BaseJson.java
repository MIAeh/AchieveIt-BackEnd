package com.achieveit.application.utils;

public class BaseJson {

    private String errorCode;
    private Object object;

    public BaseJson() {
    }

    public BaseJson(Object object) {
        this.object = object;
    }

    public BaseJson(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public BaseJson setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public BaseJson setObject(Object object) {
        this.object = object;
        return this;
    }

}
