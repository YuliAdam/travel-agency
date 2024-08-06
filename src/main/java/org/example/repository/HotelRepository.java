package org.example.repository;

import org.example.entity.Hotel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    @Query(value = "SELECT * FROM hotel", nativeQuery = true)
    List<Hotel> findAllHotel();

    @Query(value = "SELECT * FROM hotel WHERE name like %:paramtr% OR country=:paramtr OR star=:paramtr OR guests=:paramtr", nativeQuery = true)
    List<Hotel> findHotel(@Param("paramtr") String paramtr, PageRequest page);

    @Query(value = "SELECT * FROM hotel", nativeQuery = true)
    List<Hotel> findHotel(PageRequest page);

    @Query(value = "SELECT gallery FROM hotel WHERE id=:id", nativeQuery = true)
    String findGallery(@Param("id") Long id);

}
