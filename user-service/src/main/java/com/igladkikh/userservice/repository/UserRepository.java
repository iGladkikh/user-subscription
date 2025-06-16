package com.igladkikh.userservice.repository;

import com.igladkikh.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}