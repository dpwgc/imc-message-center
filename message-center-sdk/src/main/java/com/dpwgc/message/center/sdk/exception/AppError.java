package com.dpwgc.message.center.sdk.exception;

public enum AppError {

    SYS_ERROR(500, "服务器正忙,请稍后重试。"),
    ;

    private Integer code;
    private String msg;

    private AppError(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
