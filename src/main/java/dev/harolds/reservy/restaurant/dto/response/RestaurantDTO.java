package dev.harolds.reservy.restaurant.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDTO {
    private Long id;
    private String nit;
    private String name;
    private String address;
    private String city;
    private Integer tablesAvailable;
}
