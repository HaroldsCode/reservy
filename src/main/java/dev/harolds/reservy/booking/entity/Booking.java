package dev.harolds.reservy.booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "booking_id", nullable = false )
    private Long id;

    @Column( name = "reserved_tables", nullable = false )
    private Integer reservedTables;

    @Column( name = "restaurant_id", nullable = false )
    private Long restaurantId;

    @Column( name = "create_at", nullable = false )
    private LocalDateTime createAt;
}
