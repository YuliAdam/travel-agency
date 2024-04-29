package org.example.repository;

import org.example.entity.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM touristvoucher.user", nativeQuery = true)
    List<User> findAllUsers();
    @Query(value = "SELECT * FROM touristvoucher.user WHERE user_name like %:paramtr% OR email like %:paramtr% OR tell like %:paramtr%", nativeQuery = true)
    List<User> findUsers(@Param("paramtr")String paramtr, PageRequest page);

}


