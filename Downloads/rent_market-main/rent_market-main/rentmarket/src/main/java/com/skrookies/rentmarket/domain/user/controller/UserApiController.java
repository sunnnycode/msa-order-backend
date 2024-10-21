package com.skrookies.rentmarket.domain.user.controller;

import com.skrookies.rentmarket.common.api.Api;
import com.skrookies.rentmarket.domain.user.dto.LoginRequest;
import com.skrookies.rentmarket.domain.user.dto.LoginResponse;
import com.skrookies.rentmarket.domain.user.dto.RegisterRequest;
import com.skrookies.rentmarket.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserApiController {

    private final UserService userService;


    @PostMapping("/register")
    public Api<String> register(
            @RequestBody RegisterRequest request
    ){
        userService.register(request);
        return Api.OK("success");
    }


    @PostMapping("/login")
    public Api<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse response = userService.login(loginRequest);
        return Api.OK(response);
    }



}
