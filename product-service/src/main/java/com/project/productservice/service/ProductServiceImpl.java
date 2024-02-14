package com.project.productservice.service;

import com.project.productservice.dto.request.ProductPaginationParams;
import com.project.productservice.dto.request.ProductRequest;
import com.project.productservice.dto.response.PaginationBaseResponse;
import com.project.productservice.dto.response.ProductResponse;
import com.project.productservice.entity.Product;
import com.project.productservice.repository.ProductRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    public ProductRepository productRepository;

    @Autowired
    public Validator validator;

    @Override
    public PaginationBaseResponse<ProductResponse> getAllProduct(ProductPaginationParams param, Long userId) {
        Page<Product> pagingProductData = productRepository.getAllProductWithFilter(
                userId,
                param.getName(),
                param.getStartDate(),
                param.getEndDate(),
                PageRequest.of((param.getPage() - 1), param.getLimit())
        );

        Stream<Product> productStream = pagingProductData.getContent().stream();
        List<ProductResponse> productDetailList = productStream.map(productData -> ProductResponse.builder()
                .id(productData.getId())
                .name(productData.getName())
                .description(productData.getDescription())
                .price(productData.getPrice())
                .createdBy(productData.getCreatedBy())
                .createdAt(productData.getCreatedAt())
                .build()
        ).collect(Collectors.toList());

        return PaginationBaseResponse.<ProductResponse>builder()
                .currentPage(param.getPage())
                .totalPage(pagingProductData.getTotalPages())
                .totalData(pagingProductData.getTotalElements())
                .pagingData(productDetailList)
                .build();
    }

    @Override
    public ProductResponse createProduct(ProductRequest request, Long actorId) {
        validateRequest(request);

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCreatedBy(actorId);

        Product saveProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(saveProduct.getId())
                .name(saveProduct.getName())
                .description(saveProduct.getDescription())
                .price(saveProduct.getPrice())
                .createdBy(saveProduct.getCreatedBy())
                .createdAt(saveProduct.getCreatedAt())
                .build();
    }

    @Override
    public ProductResponse updateProduct(ProductRequest request, Long productId, Long actorId) {
        validateRequest(request);

        Product product = productRepository.findById(productId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        );
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setCreatedBy(actorId);

        Product updateProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(updateProduct.getId())
                .name(updateProduct.getName())
                .description(updateProduct.getDescription())
                .price(updateProduct.getPrice())
                .updatedBy(updateProduct.getCreatedBy())
                .updatedAt(updateProduct.getCreatedAt())
                .build();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private void validateRequest(Object request) {
        Set<ConstraintViolation<Object>> validation = validator.validate(request);
        if (!validation.isEmpty()) {
            throw new ConstraintViolationException(validation);
        }
    }
}
