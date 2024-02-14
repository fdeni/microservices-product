package com.project.productservice.dto.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserData {
    private Long id;

    private String username;
}
