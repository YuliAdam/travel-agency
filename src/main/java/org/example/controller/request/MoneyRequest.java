package org.example.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MoneyRequest {
    private Long id;
    @NotNull(message = "User Id cannot be empty")
    @Min(value = 1, message = "User Id is not valid.")
    private Long userId;
    @NotNull(message = "Transfer cannot be empty")
    private Float transfer;
    @NotNull(message = "Balance cannot be empty")
    @PositiveOrZero(message = "Balance cannot be negative")
    private Float balance;
    private Timestamp lastChangeDate;
}
