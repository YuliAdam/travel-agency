package org.example.repository;

import org.example.entity.Hotel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query(value = "SELECT * FROM touristvoucher.hotel",nativeQuery = true)
    List<Hotel> findAllHotel();
    @Query(value = "SELECT * FROM touristvoucher.hotel WHERE name like %:name% ",nativeQuery = true)
    List<Hotel> findHotels(@Param("name")String name, PageRequest page);
    @Query(value = "SELECT COUNT(id) FROM touristvoucher.hotel",nativeQuery = true)
    long countHotel();
}
