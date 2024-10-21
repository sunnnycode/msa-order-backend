package com.skrookies.rentmarket.domain.user.service;

import com.skrookies.rentmarket.domain.user.dto.LoginRequest;
import com.skrookies.rentmarket.domain.user.dto.LoginResponse;
import com.skrookies.rentmarket.domain.user.dto.RegisterRequest;


public interface UserService {

    void register(RegisterRequest request);
    LoginResponse login(LoginRequest loginRequest);


}

