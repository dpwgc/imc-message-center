package com.dpwgc.message.center.sdk.base;

import com.fasterxml.jackson.core.JsonProcessingException;

public class ResultDTO<T> {
    protected boolean success;
    protected Integer code;
    protected String message;
    protected T data;

    public Integer getCode() {
        return this.code;
    }

    public ResultDTO<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public ResultDTO<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultDTO() {
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return this.data;
    }

    public ResultDTO<T> setData(T data) {
        this.data = data;
        return this;
    }

    public static <T> ResultDTO<T> getSuccessResult(T v) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(true);
        result.setCode(Code.SUCCESS);
        result.setMessage("success");
        result.setData(v);
        return result;
    }

    public static <T> ResultDTO<T> getFailureResult(String msg) {
        ResultDTO<T> result = new ResultDTO();
        result.setSuccess(false);
        result.setCode(Code.ERROR);
        result.setMessage(msg);
        return result;
    }

    @Override
    public String toString() {
        try {
            return JsonUtil.toJson(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
