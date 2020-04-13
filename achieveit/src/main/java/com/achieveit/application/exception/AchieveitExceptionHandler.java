package com.achieveit.application.exception;

import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception manager for Timeline
 *
 * @author Alevery, Felix
 */
@RestControllerAdvice
public class AchieveitExceptionHandler {

    /**
     * Logger
     */
    private Logger logger = LoggerFactory.getLogger(AchieveitExceptionHandler.class);

    /**
     * Exception handler
     *
     * @param e 需要处理的异常
     * @return ResponseResult
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<Integer> handlerException(Exception e) {
        if (e == null)
            return ResultGenerator.error("fatal error!!!");
        if (e instanceof AchieveitException) {
            //如果是自定义的异常，返回对应的错误信息
            logger.info(e.getMessage());
            return ResultGenerator.error(((AchieveitException) e).getErrorCode().getCode(), e.getMessage());
        } else {
            //如果是系统异常 返回异常信息
            String errorMessage = e.getMessage();
            StackTraceElement[] elements = e.getStackTrace();
            if (e.getMessage().equals(""))
                errorMessage = elements.toString();
            logger.info(errorMessage);
            return ResultGenerator.error(ErrorCode.SYSTEM_ERROR.getCode(), errorMessage);
        }
    }
}

