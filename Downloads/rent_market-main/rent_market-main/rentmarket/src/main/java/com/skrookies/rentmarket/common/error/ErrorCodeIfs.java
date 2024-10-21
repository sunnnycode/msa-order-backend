package com.skrookies.rentmarket.common.error;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
