package com.achieveit.application.wrapper;

import com.achieveit.application.enums.ErrorCode;

/**
 * 用于构造ResponseResult
 *
 * @author Alevery, Felix
 */

public class ResultGenerator {

    private ResultGenerator() {
        throw new IllegalStateException("ResultGenerator class");
    }

    /**
     * construct success Response Result without data
     *
     * @param <T> 任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(true, 200, "success");
    }

    /**
     * construct success Response Result with string message
     *
     * @param message 传入的消息
     * @param <T>     任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> success(String message) {
        return new ResponseResult<>(true, 200, message);
    }

    /**
     * construct success Response Result with data
     *
     * @param data 传入的数据
     * @param <T>  任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(true, 200, "success", data);
    }

    /**
     * construct success Response Result with string message and data
     *
     * @param data    传入的数据
     * @param message 传入的消息
     * @param <T>     任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> success(String message, T data) {
        return new ResponseResult<>(true, 200, message, data);
    }

    /**
     * construct error Response Result with error code and string message
     *
     * @param errorCode 错误码
     * @param message   传入的消息
     * @param <T>       任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> error(int errorCode, String message) {
        return new ResponseResult<>(false, errorCode, message);
    }

    /**
     * construct error Response Result with string message
     *
     * @param message 传入的消息
     * @param <T>     任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(false, 300, message);
    }

    /**
     * construct error Response Result with error code
     *
     * @param errorCode 错误码
     * @param <T>       任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> error(int errorCode) {
        return new ResponseResult<>(false, errorCode, "error!");
    }

    /**
     * construct error Response Result with error code
     *
     * @param errorCode 错误码
     * @param <T>       任意类型
     * @return 类型为T的Response消息
     */
    public static <T> ResponseResult<T> error(ErrorCode errorCode) {
        return new ResponseResult<>(false, errorCode.getCode(), errorCode.getMsg());
    }

}
