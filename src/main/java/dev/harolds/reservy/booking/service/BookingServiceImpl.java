package dev.harolds.reservy.booking.service;

import dev.harolds.reservy.booking.dto.request.CreateBookingDTO;
import dev.harolds.reservy.booking.dto.response.BookingDTO;
import dev.harolds.reservy.booking.dto.response.BookingCreatedDTO;
import dev.harolds.reservy.booking.entity.Booking;
import dev.harolds.reservy.booking.repository.BookingRepository;
import dev.harolds.reservy.restaurant.repository.RestaurantRepository;
import dev.harolds.reservy.restaurant.service.RestaurantService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;

    private RestaurantService restaurantService;

    public BookingServiceImpl(BookingRepository bookingRepository, RestaurantService restaurantService) {
        this.bookingRepository = bookingRepository;
        this.restaurantService = restaurantService;
    }

    @Override
    public List<BookingDTO> findBookingByRestaurantId(Long restaurantId) {
        List<Booking> result = bookingRepository.findBookingByRestaurantId(restaurantId);

        if ( result.isEmpty() ) return new ArrayList<>();

        return result.stream()
                .map(booking -> BookingDTO.builder()
                        .id(booking.getId())
                        .reservedTables(booking.getReservedTables())
                        .createAt(booking.getCreateAt())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public BookingCreatedDTO saveBooking(CreateBookingDTO bookingToSave) {

        if (restaurantService.findRestaurantById(bookingToSave.getRestaurantId()) == null) return null;

        Booking saved = bookingRepository.save(
            Booking.builder()
                    .restaurantId(bookingToSave.getRestaurantId())
                    .reservedTables(bookingToSave.getReservedTables())
                    .createAt(LocalDateTime.now())
                .build()
        );
        return BookingCreatedDTO.builder()
                .id(saved.getId())
                .reservedTables(saved.getReservedTables())
                .restaurantId(saved.getRestaurantId())
                .createAt(saved.getCreateAt())
                .build();
    }

    @Override
    public boolean deleteBookingById(Long id) {
        if ( bookingRepository.findById(id).isEmpty() ) return false;
        bookingRepository.deleteById(id);
        return true;
    }
}
