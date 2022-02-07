package com.example.accounts.repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface User extends JpaRepository<User, Long> {
}
