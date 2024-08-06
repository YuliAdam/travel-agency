package org.example.service;


import lombok.RequiredArgsConstructor;
import org.example.controller.response.MoneyResponse;
import org.example.entity.Money;
import org.example.exception.BalanceException;
import org.example.exception.EntityNotExistException;
import org.example.exception.ErrorCode;
import org.example.repository.MoneyRepository;
import org.example.repository.UserRepository;
import org.example.utils.Messages;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class MoneyService {

    private final MoneyRepository moneyRepository;

    private final UserRepository userRepository;

    public MoneyResponse findByUserId(Long userId) {
        Money money = moneyRepository.findMoneyByUserId(userId);
        return (money == null) ? null : new MoneyResponse(money);
    }

    public void deleteMoney(Long userId) {
        Money moneyByUserId = moneyRepository.findMoneyByUserId(userId);
        if (moneyByUserId != null) {
            moneyRepository.deleteById(userId);
        } else {
            throw new EntityNotExistException(String.format(Messages.WALLET_NOT_EXIST, userId));
        }
    }

    public MoneyResponse createMoney(Long userId) {
        Money money = new Money(userRepository.findUserById(userId));
        money.setLastChangeDate(new Timestamp(new Date().getTime()));
        return new MoneyResponse(moneyRepository.save(money));
    }

    @Transactional
    public float update(Long userId, Float transfer) {
        Money existingMoney = moneyRepository.findMoneyByUserId(userId);
        long actualVersion = existingMoney.getVersion();
        float newBalance = existingMoney.getBalance() + transfer;
        if (newBalance < 0) throw new BalanceException(ErrorCode.BALANCE_LESS_0.toString());
        int result = moneyRepository.updateById(userId, newBalance, actualVersion, transfer, new Timestamp(new Date().getTime()));
        if (result == 0) throw new BalanceException(ErrorCode.BALANCE_NOT_UPDATED.toString());
        return newBalance;
    }

    public MoneyResponse updateMoney(Long userId, Float transfer) {
        update(userId, transfer);
        return new MoneyResponse(moneyRepository.findMoneyByUserId(userId));
    }
}