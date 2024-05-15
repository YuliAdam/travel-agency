package org.example.controller.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.characteristic.Country;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequest {
    private Long id;
    @NotNull
    private Country country;
    @NotBlank(message = "Name cannot be empty")
    @Size(min=1,max=50, message = "Country name is not valid. Max 50 chars.")
    private String name;
    @NotNull(message = "Star cannot be empty")
    @Min(value = 1, message = "Star is not valid. Min is 1 star")
    @Max(value = 5,message = "Star is not valid. Max is 5 star")
    private Integer star;
    @Min(value = 1,message = "Guests number is not valid")
    @Max(value = 100,message = "Guests number is not valid. Max is 100 guest")
    private Integer guest;
    private Boolean breakfast;
    private Boolean lunch;
    private Boolean dinner;
    private Boolean tv;
    private Boolean airConditioner;
    private Boolean balcony;
}
