package com.achieveit.application.exception;

        import com.achieveit.application.enums.ErrorCode;

        import java.util.Objects;

public class AchieveitException extends RuntimeException {

    private ErrorCode errorCode;

    public AchieveitException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "AchieveitException{" +
                "errorCode=" + errorCode +
                '}';
    }
}
