package com.project.productservice.controller;

import com.project.productservice.dto.data.UserData;
import com.project.productservice.dto.request.ProductPaginationParams;
import com.project.productservice.dto.request.ProductRequest;
import com.project.productservice.dto.response.Base;
import com.project.productservice.dto.response.PaginationBaseResponse;
import com.project.productservice.dto.response.ProductResponse;
import com.project.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    private PaginationBaseResponse<ProductResponse> getAllProduct(
            UserData userData, @ModelAttribute ProductPaginationParams params
    ) {
        if (params.getPage() == null) {
            params.setPage(1);
        }
        if (params.getLimit() == null) {
            params.setLimit(10);
        }
        return productService.getAllProduct(params, userData.getId());
    }

    @PostMapping
    private Base<ProductResponse> createProduct(UserData userData, @RequestBody ProductRequest request) {
        return Base.<ProductResponse>builder()
                .data(productService.createProduct(request, userData.getId()))
                .status(HttpStatus.OK.value())
                .build();
    }

    @PutMapping("/{productId}")
    private Base<ProductResponse> updateProduct(
            UserData userData,
            @RequestBody ProductRequest request,
            @PathVariable Long productId
    ) {
        return Base.<ProductResponse>builder()
                .data(productService.updateProduct(request, productId, userData.getId()))
                .status(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/{productId}")
    private Base<String> deleteProduct(UserData userData, @PathVariable Long productId) {
        productService.deleteProduct(productId);
        return Base.<String>builder()
                .data("Delete product successful")
                .status(HttpStatus.OK.value())
                .build();
    }
}
