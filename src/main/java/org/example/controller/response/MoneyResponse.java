package org.example.controller.response;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.Money;

import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyResponse {
        private Long id;
        private Long userId;
        private Float transfer;
        private Float balance;
        private Timestamp lastChangeDate;
        private long version;
        public MoneyResponse(Money money){
            this.id= money.getId();
            this.userId= money.getUser().getId();
            this.balance= money.getBalance();
            this.transfer= money.getTransfer();
            this.lastChangeDate=money.getLastChangeDate();
            this.version=money.getVersion();
        }
}
