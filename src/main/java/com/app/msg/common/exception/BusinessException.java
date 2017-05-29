package com.app.msg.common.exception;


import com.app.msg.common.BaseEnum;

/**
 * 功能描述:业务类异常
 */
public class BusinessException extends RuntimeException {
    private String code;

    public BusinessException() {
        super();
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
    }

    public BusinessException(BaseEnum errorCode) {
        super(errorCode.getDesc());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
