package org.example.repository;

import org.example.controller.response.HotelResponse;
import org.example.entity.Hotel;
import org.example.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    @Query(value = "SELECT * FROM touristvoucher.offer", nativeQuery = true)
    public List<Offer> findAllOffer ();
    @Query(value = "SELECT id FROM touristvoucher.hotel", nativeQuery = true)
    public List<Long> findAllHotelId ();
    @Query(value = "SELECT * FROM touristvoucher.hotel", nativeQuery = true)
    public List<Hotel> findAllHotel ();
    @Query(value = "SELECT * FROM touristvoucher.hotel WHERE id=:hotelId", nativeQuery = true)
    public Hotel findHotelById (@Param("hotelId") Long hotelId);

}
