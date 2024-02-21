package org.example.repository;

import org.example.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query(value = "SELECT * FROM touristvoucher.hotel",nativeQuery = true)
    List<Hotel> findAllHotel();
}
