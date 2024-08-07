package org.example.repository;

import org.example.entity.Hotel;
import org.example.entity.Offer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query(value = "SELECT * FROM touristvoucher.offer", nativeQuery = true)
    List<Offer> findAllOffer();

    @Query(value = "SELECT id FROM touristvoucher.hotel", nativeQuery = true)
    List<Long> findAllHotelId();

    @Query(value = "SELECT * FROM touristvoucher.hotel", nativeQuery = true)
    List<Hotel> findAllHotel();

    @Query(value = "SELECT * FROM touristvoucher.hotel WHERE id=:hotelId", nativeQuery = true)
    Hotel findHotelById(@Param("hotelId") Long hotelId);

    @Query(value = "SELECT * FROM touristvoucher.offer WHERE type=:paramtr OR country=:paramtr OR transport=:paramtr", nativeQuery = true)
    List<Offer> findOffer(@Param("paramtr") String paramtr, PageRequest page);

    @Query(value = "SELECT * FROM touristvoucher.offer", nativeQuery = true)
    List<Offer> findOffer(PageRequest page);

    @Query(value = "SELECT * FROM touristvoucher.offer WHERE id=:offerId", nativeQuery = true)
    Offer findOfferById(@Param("offerId") Long offerId);

}
