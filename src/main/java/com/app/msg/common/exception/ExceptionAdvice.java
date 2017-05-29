package com.app.msg.common.exception;

import com.app.msg.interfaces.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by infear on 2017/5/29.
 */
@ControllerAdvice
public class ExceptionAdvice {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public BaseResponse handleBusinessException(HttpServletRequest request, BusinessException businessException) {
        if (request != null) {
            logger.warn("Request:{}, Remote Address:{}", request.getRequestURL(), request.getRemoteAddr());
        }
        logger.error(businessException.getMessage(), businessException);
        return BaseResponse.newFailResponse(businessException.getCode(), businessException.getMessage());
    }
}
