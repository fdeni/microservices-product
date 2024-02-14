package com.project.authservice.controller;

import com.project.authservice.dto.data.AuthenticationData;
import com.project.authservice.dto.request.LoginRequest;
import com.project.authservice.dto.request.RegisterRequest;
import com.project.authservice.dto.response.Base;
import com.project.authservice.dto.response.LoginResponse;
import com.project.authservice.dto.response.RegisterResponse;
import com.project.authservice.service.UserDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
@RestController
public class UserDataController {
    @Autowired
    private UserDataService userDataService;

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Base<RegisterResponse> register(@RequestBody RegisterRequest request) {
        return Base.<RegisterResponse>builder()
                .data(userDataService.register(request))
                .status(HttpStatus.OK.value())
                .build();
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Base<LoginResponse> login(@RequestBody LoginRequest request) {
        return Base.<LoginResponse>builder()
                .data(userDataService.login(request))
                .status(HttpStatus.OK.value())
                .build();
    }

    @GetMapping(path = "/validate")
    public Base<AuthenticationData> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authStr) {
        String token = authStr.substring("Bearer ".length());
        return Base.<AuthenticationData>builder()
                .data(userDataService.validateToken(token))
                .status(HttpStatus.OK.value())
                .build();
    }

}
