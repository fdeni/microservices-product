package com.project.authservice.service;

import com.project.authservice.dto.request.LoginRequest;
import com.project.authservice.dto.request.RegisterRequest;
import com.project.authservice.entity.UserData;
import com.project.authservice.repository.UserDataRepository;
import com.project.authservice.utils.Jwt;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserDataServiceImplTests {
    @InjectMocks
    private UserDataServiceImpl userDataService;

    @Mock
    private UserDataRepository userDataRepository;

    @Mock
    private Jwt jwt;

    @Mock
    private Validator validator;

    private RegisterRequest registerRequest = RegisterRequest.builder().build();
    private LoginRequest loginRequest = LoginRequest.builder().build();

    private Long userId = 1L;

    @Test
    public void registerUser_whenUsernameAlreadyExists_shouldThrowException() {
        when(userDataRepository.existsByUsername(any())).thenReturn(true);

        Throwable exception = Assertions.assertThrows(
                ResponseStatusException.class, (() -> userDataService.register(registerRequest))
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST + " \"Username already exists\"", exception.getMessage());
    }

    @Test
    public void registerUser_shouldCall_validator_and_UserDataRepository() {
        UserData userData = new UserData();
        when(userDataRepository.existsByUsername(any())).thenReturn(false);
        when(userDataRepository.save(any())).thenReturn(userData);

        userDataService.register(registerRequest);

        Mockito.verify(validator, times(1)).validate(any());
        Mockito.verify(userDataRepository, times(1)).existsByUsername(any());
        Mockito.verify(userDataRepository, times(1)).save(any());
    }


    @Test
    public void login_whenUsernameDoesNotExists_shouldThrowException() {
        Throwable exception = Assertions.assertThrows(
                ResponseStatusException.class, (() -> userDataService.login(loginRequest))
        );

        Assertions.assertEquals(
                HttpStatus.BAD_REQUEST + " \"Invalid username/password\"",
                exception.getMessage()
        );
    }

    @Test
    public void validateToken_shouldCall_jwtValidateToken() {
        userDataService.validateToken("token");

        verify(jwt, times(1)).validateToken("token");
    }
}
