package com.project.productservice.repository;

import com.project.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(
            value = "SELECT * FROM mr_product " +
                    "WHERE mp_created_by = :userId " +
                    "AND (:name IS NULL OR mp_name = :name) " +
                    "AND (:startDate IS NULL OR mp_created_at >= :startDate) " +
                    "AND (:endDate IS NULL OR mp_created_at <= :endDate) ",
            countQuery = "SELECT COUNT(*) FROM mp_product " +
                    "WHERE mp_created_by = :userId " +
                    "AND (:name IS NULL OR mp_name = :name) " +
                    "AND (:startDate IS NULL OR mp_created_at >= :startDate) " +
                    "AND (:endDate IS NULL OR mp_created_at <= :endDate) ",
            nativeQuery = true
    )
    public Page<Product> getAllProductWithFilter(
            @Param("userId") Long userId,
            @Param("name") String name,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            Pageable pageable
    );
}
