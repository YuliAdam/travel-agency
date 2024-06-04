package org.example.repository;

import org.example.controller.response.HotelResponse;
import org.example.entity.Hotel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Query(value = "SELECT * FROM touristvoucher.hotel",nativeQuery = true)
    List<Hotel> findAllHotel();
    @Query(value = "SELECT * FROM touristvoucher.hotel WHERE name like %:paramtr% OR country=:paramtr OR star=:paramtr OR guests=:paramtr",nativeQuery = true)
    List<Hotel> findHotels(@Param("paramtr")String paramtr, PageRequest page);
    @Query(value = "SELECT * FROM touristvoucher.hotel",nativeQuery = true)
    List<Hotel> findHotels(PageRequest page);
    @Query(value = "SELECT COUNT(id) FROM touristvoucher.hotel",nativeQuery = true)
    long countHotel();
    @Query(value = "SELECT gallery FROM touristvoucher.hotel WHERE id=:id", nativeQuery = true)
    public String findGallery (@Param("id") Long id);

}
