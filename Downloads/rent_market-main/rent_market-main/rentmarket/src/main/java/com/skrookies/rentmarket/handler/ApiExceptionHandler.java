package com.skrookies.rentmarket.handler;

import com.skrookies.rentmarket.common.api.Api;
import com.skrookies.rentmarket.common.error.ErrorCodeIfs;
import com.skrookies.rentmarket.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestController
@Order(value = Integer.MIN_VALUE)
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public Api<Object> apiResponseEntity(ApiException apiException){
        log.debug("", apiException);
        ErrorCodeIfs errorCode = apiException.getErrorCodeIfs();
        return Api.ERROR(errorCode,apiException.getErrorDescription());
    }
}