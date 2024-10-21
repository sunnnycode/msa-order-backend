package com.skrookies.rentmarket.domain.user.service;

import com.skrookies.rentmarket.common.error.ErrorCode;
import com.skrookies.rentmarket.common.exception.ApiException;
import com.skrookies.rentmarket.common.utils.JwtUtils;
import com.skrookies.rentmarket.domain.user.dto.LoginRequest;
import com.skrookies.rentmarket.domain.user.dto.LoginResponse;
import com.skrookies.rentmarket.domain.user.dto.RegisterRequest;
import com.skrookies.rentmarket.domain.user.entity.UserEntity;
import com.skrookies.rentmarket.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtUtils jwtUtils;

    // 회원가입
    @Override
    public void register(RegisterRequest request) {
        UserEntity entity = new ModelMapper().map(request, UserEntity.class);
        entity.setCreatedAt(LocalDateTime.now());
        //password 암호화
        entity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        userRepository.save(entity);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        UserEntity userEntity = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ApiException(ErrorCode.BAD_REQUEST));
        boolean passwordMatch = bCryptPasswordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword());
        if (!passwordMatch) {
            throw new ApiException(ErrorCode.BAD_REQUEST);
        }
        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtUtils.generateToken(userEntity))
                .build();
        return loginResponse;

    }
}