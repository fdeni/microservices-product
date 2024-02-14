package com.project.authservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "ud_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ud_username")
    private String username;

    @Column(name = "ud_password")
    private String password;

    @Column(name = "ud_name")
    private String name;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ud_created_at")
    private Date createdAt;
}
