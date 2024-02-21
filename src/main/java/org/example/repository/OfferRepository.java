package org.example.repository;

import org.example.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    @Query(value = "SELECT * FROM touristvoucher.offer", nativeQuery = true)
    public List<Offer> findAllOffer ();
}
