package org.example.service;

import org.example.entity.Money;
import org.example.entity.Users;
import org.example.repository.MoneyRepository;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class MoneyServiceTest {

    MoneyService moneyService;
    @Mock
    MoneyRepository moneyRepository;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        this.moneyService = new MoneyService(moneyRepository, userRepository);
    }

    @Test
    void testUpdateMoneyWhenBalanceLessThenZero() {
        when(moneyRepository.findMoneyByUserId(1l)).thenReturn(new Money(100f, 1));
        assertThrows(RuntimeException.class, () -> moneyService.update(1l, -500f));

    }

    @Test
    void testUpdateMoneyWhenNotUpdated() {
        when(moneyRepository.findMoneyByUserId(1l)).thenReturn(new Money(100f, 1));
        when(moneyRepository.updateById(eq(1l), eq(100f), eq(1), eq(500f), any())).thenReturn(0);
        assertThrows(RuntimeException.class, () -> moneyService.update(1l, 500f));
    }

    @Test
    void testUpdateMoneyWhenUpdated() {
        when(moneyRepository.findMoneyByUserId(1l)).thenReturn(new Money(Users.builder().id(1l).build()));
        when(moneyRepository.updateById(eq(1l), eq(600.0f), eq(0), eq(500.0f), any(Timestamp.class))).thenReturn(1);
        Float result = moneyService.update(1l, 500.0f);
        assertEquals(600.0f, result);
        verify(moneyRepository).updateById(eq(1l), eq(600.0f), eq(0), eq(500.0f), any(Timestamp.class));
    }
}