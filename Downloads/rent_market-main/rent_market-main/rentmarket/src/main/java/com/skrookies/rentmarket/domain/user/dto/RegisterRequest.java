package com.skrookies.rentmarket.domain.user.dto;

import lombok.Data;

@Data
public class RegisterRequest {

    private String username;

    private String email;

    private String password;

    private String passwordConfirm;

    private int phoneNumber;

    private String location;

    public boolean checkPassword() {
        return this.password != null && this.password.equals(this.passwordConfirm);
    }


}
