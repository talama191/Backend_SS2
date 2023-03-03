package com.example.ecommercebackend.User;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "Select * from user where user.email=?1", nativeQuery = true)
    public User getUserByEmail(String email);
    @Query(value = "Select * from user where user.username=?1", nativeQuery = true)
    public User getUserByUsername(String username);
}
