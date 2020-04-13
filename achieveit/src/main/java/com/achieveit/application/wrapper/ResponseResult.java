package com.achieveit.application.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

/**
 * 返回的Response消息
 *
 * @author Alevery, Felix
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {

    /**
     * 请求是否成功
     */
    private boolean success;

    /**
     * 错误码
     */
    private int errorCode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public ResponseResult() {
    }

    ResponseResult(boolean success, int errorCode, String message) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
    }

    ResponseResult(boolean success, int errorCode, String message, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseResult<?> that = (ResponseResult<?>) o;
        return isSuccess() == that.isSuccess() &&
                getErrorCode() == that.getErrorCode() &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getData(), that.getData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(isSuccess(), getErrorCode(), getMessage(), getData());
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "success=" + success +
                ", errorCode=" + errorCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
