package com.achieveit.application.exception;

import com.achieveit.application.enums.ErrorCode;

import static com.achieveit.application.enums.ErrorCode.UNKNOWN_ERROR;


public class AchieveitException extends RuntimeException {

    private ErrorCode errorCode;

    private String message;

    public AchieveitException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = this.errorCode.getMsg();
    }

    public AchieveitException(String message) {
        this.errorCode = UNKNOWN_ERROR;
        this.message = message;
    }

    public AchieveitException(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AchieveitException{" +
                "errorCode=" + errorCode +
                ", message='" + message + '\'' +
                '}';
    }
}
