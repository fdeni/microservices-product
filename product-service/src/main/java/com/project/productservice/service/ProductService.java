package com.project.productservice.service;

import com.project.productservice.dto.request.ProductPaginationParams;
import com.project.productservice.dto.request.ProductRequest;
import com.project.productservice.dto.response.PaginationBaseResponse;
import com.project.productservice.dto.response.ProductResponse;

public interface ProductService {
    public PaginationBaseResponse<ProductResponse> getAllProduct(ProductPaginationParams param, Long userId);
    public ProductResponse createProduct(ProductRequest request, Long actorId);
    public ProductResponse updateProduct(ProductRequest request, Long productId, Long actorId);
    public void deleteProduct(Long productId);
}
