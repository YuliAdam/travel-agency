package org.example.repository;

import org.example.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Integer> {
    @Query(value = "SELECT * FROM touristvoucher.offer", nativeQuery = true)
    public List<Offer> findAllOffer ();
    @Query(value = "SELECT id,type,country,num_of_the_days,start,transport,id_hotel,price$ FROM touristvoucher.offer", nativeQuery = true)
    public List<Offer> findAllOffer1 ();
}
