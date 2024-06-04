package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.entity.Money;
import org.example.repository.MoneyRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.controller.response.MoneyResponse;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MoneyService {

    private final MoneyRepository moneyRepository;

    private final UserRepository userRepository;

    public MoneyResponse findByUserId(Long userId) {
        if (moneyRepository.findMoneyByUserId(userId) == null) return null;
        else return new MoneyResponse(moneyRepository.findMoneyByUserId(userId));
    }

    public String deleteMoney(Long userId) {
        moneyRepository.deleteById(userId);
        return "Wallet deleted.";
    }

    public MoneyResponse createMoney(Long userId) {
        Money money = new Money(userRepository.findUserById(userId), 0f, 0f);
        money.setLastChangeDate(new Timestamp(new Date().getTime()));
        return new MoneyResponse(moneyRepository.save(money));
    }

    public float update(Long userId, Float transfer) {
        Money existingMoney = moneyRepository.findMoneyByUserId(userId);
        long actualVersion = existingMoney.getVersion();
        float newBalance = existingMoney.getBalance() + transfer;
        if (newBalance < 0) throw new RuntimeException("Balance < 0");
        int result = moneyRepository.updateById(userId, newBalance, actualVersion, transfer, new Timestamp(new Date().getTime()));
        if (result == 0) throw new RuntimeException("Not updated, try again");
        return newBalance;
    }
    public MoneyResponse updateMoney(Long userId, Float transfer) {
        update(userId,transfer);
        return new MoneyResponse(moneyRepository.findMoneyByUserId(userId));
    }
}