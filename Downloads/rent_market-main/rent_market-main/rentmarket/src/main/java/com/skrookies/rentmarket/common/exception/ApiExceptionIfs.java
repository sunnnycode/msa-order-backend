package com.skrookies.rentmarket.common.exception;

import com.skrookies.rentmarket.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();
    String getErrorDescription();
}
