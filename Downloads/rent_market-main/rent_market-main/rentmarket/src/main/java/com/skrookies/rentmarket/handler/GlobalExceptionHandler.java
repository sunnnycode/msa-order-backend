package com.skrookies.rentmarket.handler;

import com.skrookies.rentmarket.common.api.Api;
import com.skrookies.rentmarket.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestController
@Order(value = Integer.MAX_VALUE)
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Api<Object> exception(
            Exception exception
    ){
        log.info("",exception);
        return Api.ERROR(ErrorCode.SERVER_ERROR);
    }
}