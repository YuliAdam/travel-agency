package org.example.repository;

import org.example.entity.Users;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    @Query(value = "SELECT * FROM touristvoucher.user", nativeQuery = true)
    List<Users> findAllUsers();
    @Query(value = "SELECT * FROM touristvoucher.user WHERE user_name like %:paramtr% OR login like %:paramtr% OR tell like %:paramtr%", nativeQuery = true)
    List<Users> findUsers(@Param("paramtr")String paramtr, PageRequest page);
    Users findByLogin(String login);
    @Query(value = "SELECT * FROM touristvoucher.user WHERE id=:userId", nativeQuery = true)
    public Users findUserById (@Param("userId") Long userId);



}


