package dev.harolds.reservy.restaurant.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "restaurant" )
public class Restaurant {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "restaurant_id", nullable = false )
    private Long id;

    @Column( name = "restaurant_nit", unique = true, nullable = false)
    private String nit;

    @Column( name = "restaurant_name", nullable = false)
    private String name;

    @Column( name = "address", nullable = false)
    private String address;

    @Column( name = "city", nullable = false)
    private String city;

    @Column( name = "tables", nullable = false )
    private Integer tablesAvailable;
}
