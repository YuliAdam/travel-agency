package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT user.id,user.user_name,user.email,user.tell FROM touristvoucher.order LEFT JOIN touristvoucher.user ON user.id=order.user_id", nativeQuery = true)
    List<User> findAllUsers();

}


