package com.example.opticalStore.repositories;

import com.example.opticalStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
