package org.example.repository;

import org.example.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    @Query(value = "SELECT * FROM touristvoucher.admin", nativeQuery = true)
    public List<Admin> findAllAdmin ();
}
