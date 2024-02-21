package org.example.repository;

import org.example.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    @Query(value = "SELECT * FROM touristvoucher.orders", nativeQuery = true)
    public List<Orders> findAllOrders();
}
