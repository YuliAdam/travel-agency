package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    @Modifying
    @Query(value = "DELETE FROM touristvoucher.money where id_user=:user_id", nativeQuery = true)
    void deleteById(@Param("user_id") Long userId);

    @Query(value = "SELECT * FROM money WHERE id_user=:user_id", nativeQuery = true)
    Money findMoneyByUserId(@Param("user_id") Long userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE touristvoucher.money SET balance=:balance, version=version+1, last_transfer=:transfer, last_change_date=:timeNow WHERE id_user=:user_id and version=:version", nativeQuery = true)
    int updateById(@Param("user_id") Long userId, @Param("balance") Float balance, @Param("version") long version, @Param("transfer") Float transfer, @Param("timeNow") Timestamp timeNow);
}