package com.project.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "mr_product")
public class Product {
    @Id
    @Column(name = "mp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mp_name")
    private String name;

    @Column(name = "mp_description")
    private String description;

    @Column(name = "mp_price")
    private Integer price;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mp_created_at")
    private Date createdAt;

    @Column(name = "mp_created_by")
    private Long createdBy;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "mp_updated_at")
    private Date updatedAt;

    @Column(name = "mp_updated_by")
    private Long updatedBy;
}
