package com.project.productservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {
    @NotNull
    private String name;

    @NotNull
    private String description;

    @Min(value = 0)
    private int price;
}
