package dev.harolds.reservy.booking.repository;

import dev.harolds.reservy.booking.dto.response.BookingDTO;
import dev.harolds.reservy.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findBookingByRestaurantId( Long restaurantId );

}
