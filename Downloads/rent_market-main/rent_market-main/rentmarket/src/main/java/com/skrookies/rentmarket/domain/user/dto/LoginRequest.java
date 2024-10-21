package com.skrookies.rentmarket.domain.user.dto;

import lombok.Data;

@Data
public class LoginRequest {

    String username;
    String password;

}
