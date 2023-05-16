package com.example.ecommercebackend.Entities.User;

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

    @Query(value = "SELECT distinct role_id FROM user_roles where user_id=?1", nativeQuery = true)
    public Integer getRoleByUserID(int user_id);

    @Modifying
    @Query(value = "insert into user_roles (user_id,role_id) values (:user_id,:role_id)", nativeQuery = true, countQuery = "select 1")
    @Transactional
    public void setUserRole(@Param("user_id") int user_id, @Param("role_id") int role_id);

    @Query(value = "select * from user where user.user_id=?1", nativeQuery = true)
    public User getUserByUserID(int user_id);
}
