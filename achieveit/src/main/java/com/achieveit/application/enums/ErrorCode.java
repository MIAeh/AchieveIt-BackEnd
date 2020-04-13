package com.achieveit.application.enums;

public enum ErrorCode {

    UNKNOWN_ERROR(-1, "Unknown Error"),
    SUCCESS(0, "Success"),
    INSERTION_ERROR(100, "Fail to Insert"),
    UPDATE_ERROR(101, "Fail to Update"),
    DELETION_ERROR(102, "Fail to Delete"),
    QUERY_ERROR(103, "Fail to Query"),
    SESSION_ERROR(401, "Login Without UserID Session Attribute"),
    UNAUTHORIZED(402, "Access Deny,Login First"),
    STATUS_ERROR(1, "Project Status Error"),
    ROLE_ERROR(2, "Unauthorized User Role"),
    ARCHIVE_ERROR(3, "Project No Archive Error"),
    POST_CONTROL_AOP_ERROR(4, "Fail to Get Project ID"),
    POST_CONTROL_ERROR(5, "Fail to Post, Project Archived"),
    SYSTEM_ERROR(500,"System Error!");

    private Integer code;

    private String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}

