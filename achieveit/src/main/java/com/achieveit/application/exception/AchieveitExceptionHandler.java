package com.achieveit.application.exception;

import com.achieveit.application.wrapper.ResponseResult;
import com.achieveit.application.wrapper.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Exception manager for Timeline
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
     * @param e 需要处理的异常
     * @return ResponseResult
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult<Integer> handlerException(Exception e) {
        /*
            如果是自定义的异常，返回对应的错误信息
         */
        if (e instanceof AchieveitException) {
            logger.info(e.getMessage());
            return ResultGenerator.error(((AchieveitException) e).getErrorCode().getCode(), e.getMessage());
        } else {
            /*
                如果不是已知异常，返回系统异常
             */
            logger.info(e.getMessage());
            return ResultGenerator.error(200, "System Error!");
        }
    }
}
