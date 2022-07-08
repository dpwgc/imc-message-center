package com.dpwgc.message.center.sdk.exception;

/**
 * @author cah48279
 * @description TODO
 * @date 2021/5/17 15:20
 */
public class AppException extends RuntimeException {

    private Integer code;

    public AppException(AppError appError) {
        super(appError.getMsg());
    }

    public Integer getCode() {
        return this.code;
    }
}
