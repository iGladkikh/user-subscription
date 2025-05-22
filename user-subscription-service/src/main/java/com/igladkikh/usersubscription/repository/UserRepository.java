package com.igladkikh.usersubscription.repository;

import com.igladkikh.usersubscription.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}