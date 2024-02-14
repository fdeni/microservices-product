package com.project.productservice.service;

import com.project.productservice.dto.request.ProductPaginationParams;
import com.project.productservice.dto.request.ProductRequest;
import com.project.productservice.repository.ProductRepository;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceImplTests {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private Validator validator;

    private ProductRequest request = ProductRequest
            .builder()
            .name("rinso")
            .description("sabun cuci")
            .build();

    private ProductPaginationParams params = ProductPaginationParams.builder()
            .page(1)
            .limit(10)
            .build();

    private Long userId = 1L;
    private Long productId = 1L;

    @Test
    public void updateProductData_whenExpenseDataDoesNotExists_thenThrow() {
        Throwable exception = Assertions.assertThrows(
                ResponseStatusException.class, () -> productService.updateProduct(request, productId, userId)
        );

        Assertions.assertEquals(HttpStatus.NOT_FOUND+" \"Product not found\"", exception.getMessage());
    }

    @Test
    public void deleteProductData_shouldCall_deleteFromProductDataRepository() {
        productService.deleteProduct(productId);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(productId);
    }

    @Test
    public void getAllProduct_shouldCall_getAllProductWithFilter() {
        when(productRepository.getAllProductWithFilter(any(), any(), any(), any(), any())).thenReturn(
                Page.empty()
        );

        productService.getAllProduct(params, userId);

        Mockito.verify(productRepository, Mockito.times(1)).getAllProductWithFilter(
                any(), any(), any(), any(), any()
        );
    }
}
