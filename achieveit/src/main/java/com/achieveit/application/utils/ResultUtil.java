package com.achieveit.application.utils;

import com.achieveit.application.domain.Result;
import com.achieveit.application.enums.ErrorCode;

public class ResultUtil {

    public static Result success(Object object) {
        return new Result(ErrorCode.SUCCESS, object);
    }

    public static Result success() {
        return new Result(ErrorCode.SUCCESS, null);
    }

    public static Result error(ErrorCode errorCode) {
        return new Result(errorCode);
    }

}
