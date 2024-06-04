package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.entity.Money;
import org.example.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    @Query(value = "SELECT * FROM touristvoucher.money", nativeQuery = true)
    List<Money> findAllMoney();
    @Modifying
    @Query(value = "DELETE FROM touristvoucher.money where id_user=:user_id", nativeQuery = true)
    public void deleteById(@Param("user_id") Long userId);
    @Query(value = "SELECT * FROM touristvoucher.money WHERE id_user=:user_id", nativeQuery = true)
    public Money findMoneyByUserId (@Param("user_id") Long userId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE touristvoucher.money SET balance=:balance, version=version+1, last_transfer=:transfer, last_change_date=:timeNow WHERE id_user=:user_id and version=:version", nativeQuery = true)
    public boolean updateById (@Param("user_id") Long userId, @Param("balance") Float balance, @Param("version") long version, @Param("transfer") Float transfer, @Param("timeNow")Timestamp timeNow);
}