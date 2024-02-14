package com.project.productservice.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Base<T> {
    private T data;

    private String error;

    private int status;
}
