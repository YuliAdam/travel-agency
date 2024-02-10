package org.example.repository;

import org.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>
{
@Query(value = "SELECT * FROM touristvoucher.user",nativeQuery = true)
List<User> findAllUsers();
}

