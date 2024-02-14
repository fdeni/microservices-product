package com.project.authservice.service;

import com.project.authservice.dto.data.AuthenticationData;
import com.project.authservice.dto.request.LoginRequest;
import com.project.authservice.dto.request.RegisterRequest;
import com.project.authservice.dto.response.LoginResponse;
import com.project.authservice.dto.response.RegisterResponse;
import com.project.authservice.entity.UserData;
import com.project.authservice.repository.UserDataRepository;
import com.project.authservice.utils.Jwt;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
public class UserDataServiceImpl implements UserDataService{

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private Jwt jwt;

    @Autowired
    private Validator validator;

    @Override
    public RegisterResponse register(RegisterRequest request) {
        validateRequest(request);

        if (userDataRepository.existsByUsername(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        UserData user = new UserData();
        user.setUsername(request.getUsername());
        user.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        user.setName(request.getName());

        UserData savedUserData = userDataRepository.save(user);

        return RegisterResponse.builder()
                .username(savedUserData.getUsername())
                .name(savedUserData.getName())
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        validateRequest(request);

        UserData user = userDataRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username/password")
                );

        if (BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            String token = jwt.generateToken(user);

            return LoginResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .name(user.getName())
                    .token(token)
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username/password");
        }
    }

    @Override
    public AuthenticationData validateToken(String token) {
        return jwt.validateToken(token);
    }

    private void validateRequest(Object request) {
        Set<ConstraintViolation<Object>> validation = validator.validate(request);
        if (!validation.isEmpty()) {
            throw new ConstraintViolationException(validation);
        }
    }
}
