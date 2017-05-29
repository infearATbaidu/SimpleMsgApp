/**
 * Project Name:movie-base
 * File Name:BaseResponse.java
 * Package Name:com.baidu.movie.base
 * Date:2014年12月19日下午2:29:49
 * Copyright (c) 2014, www.baidu.com All Rights Reserved.
 */
package com.app.msg.interfaces;

import com.app.msg.common.exception.ErrorCode;

import java.util.Map;

/**
 * Http BaseResponse
 */
public class BaseResponse<T> {
    protected boolean success;
    protected String errorCode;
    protected String errorMsg;
    protected T result;
    protected Map extension;

    public BaseResponse() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Map getExtension() {
        return extension;
    }

    public void setExtension(Map extension) {
        this.extension = extension;
    }

    public static <T> BaseResponse<T> newSuccResponse(T result, Map extension) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setSuccess(true);
        response.setErrorCode(ErrorCode.SUCCESS.getCode());
        response.setResult(result);
        response.setExtension(extension);
        return response;
    }

    public static <T> BaseResponse<T> newSuccResponse(T result) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setSuccess(true);
        response.setErrorCode(ErrorCode.SUCCESS.getCode());
        response.setResult(result);
        return response;
    }

    public static <T> BaseResponse<T> newFailResponse(T result) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setSuccess(false);
        response.setErrorCode(ErrorCode.FAIL.getCode());
        response.setResult(result);
        return response;
    }

    public static <T> BaseResponse<T> newFailResponse(String errorCode, String errorMsg) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setErrorMsg(errorMsg);
        return response;
    }

    public static <T> BaseResponse<T> newFailResponse(ErrorCode errorCode) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setSuccess(false);
        response.setErrorCode(errorCode.getCode());
        response.setErrorMsg(errorCode.getDesc());
        return response;
    }

    public static <T> BaseResponse<T> newFailResponse(ErrorCode errorCode, T result) {
        BaseResponse<T> response = new BaseResponse<T>();
        response.setSuccess(false);
        response.setErrorCode(errorCode.getCode());
        response.setErrorMsg(errorCode.getDesc());
        response.setResult(result);
        return response;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseResponse{");
        sb.append("success=").append(success);
        sb.append(", errorCode='").append(errorCode).append('\'');
        sb.append(", errorMsg='").append(errorMsg).append('\'');
        sb.append(", result=").append(result);
        sb.append(", extension=").append(extension);
        sb.append('}');
        return sb.toString();
    }
}
