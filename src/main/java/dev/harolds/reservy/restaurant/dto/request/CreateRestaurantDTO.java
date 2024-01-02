package dev.harolds.reservy.restaurant.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRestaurantDTO {
    @NonNull
    private String nit;
    @NonNull
    private String name;
    @NonNull
    private String address;
    @NonNull
    private String city;
    @NonNull
    private Integer tablesAvailable;
}
