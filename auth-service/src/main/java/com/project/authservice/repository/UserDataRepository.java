package com.project.authservice.repository;

import com.project.authservice.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
    public boolean existsByUsername(String username);

    public Optional<UserData> findByUsername(String username);
}
