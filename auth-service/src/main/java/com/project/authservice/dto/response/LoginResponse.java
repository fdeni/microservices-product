package com.project.authservice.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "username")
    private String username;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "token")
    private String token;
}
