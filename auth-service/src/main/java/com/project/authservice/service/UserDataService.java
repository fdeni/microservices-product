package com.project.authservice.service;

import com.project.authservice.dto.data.AuthenticationData;
import com.project.authservice.dto.request.LoginRequest;
import com.project.authservice.dto.request.RegisterRequest;
import com.project.authservice.dto.response.LoginResponse;
import com.project.authservice.dto.response.RegisterResponse;

public interface UserDataService {
    public RegisterResponse register(RegisterRequest request);

    public LoginResponse login(LoginRequest request);

    public AuthenticationData validateToken(String token);
}
