package org.example.repository;

import org.example.entity.Hotel;
import org.example.entity.Offer;
import org.example.entity.Orders;
import org.example.entity.Users;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {
    @Query(value = "SELECT * FROM touristvoucher.orders", nativeQuery = true)
    public List<Orders> findAllOrders();
    @Query(value = "SELECT * FROM touristvoucher.orders", nativeQuery = true)
    public List<Orders> findOrders(PageRequest page);
    @Query(value = "SELECT * FROM touristvoucher.orders WHERE id=:paramtr OR user_id=:paramtr OR offer_id=:paramtr", nativeQuery = true)
    List<Orders> findOrders(@Param("paramtr")String paramtr, PageRequest page);
    @Query(value = "SELECT * FROM touristvoucher.user WHERE id=:userId", nativeQuery = true)
    public Users findUserById (@Param("userId") Long userId);
    @Query(value = "SELECT * FROM touristvoucher.offer WHERE id=:offerId", nativeQuery = true)
    public Offer findOfferById (@Param("offerId") Long offerId);
}
