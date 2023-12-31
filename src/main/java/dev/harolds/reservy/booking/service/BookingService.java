package dev.harolds.reservy.booking.service;

import dev.harolds.reservy.booking.dto.request.CreateBookingDTO;
import dev.harolds.reservy.booking.dto.response.BookingDTO;
import dev.harolds.reservy.booking.dto.response.BookingCreatedDTO;

import java.util.List;

public interface BookingService {
    public List<BookingDTO> findBookingByRestaurantId(Long restaurantId );

    public BookingCreatedDTO saveBooking(CreateBookingDTO bookingToSave );

    public boolean deleteBookingById(Long id );
}
