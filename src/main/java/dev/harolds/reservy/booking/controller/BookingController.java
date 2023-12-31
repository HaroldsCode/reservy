package dev.harolds.reservy.booking.controller;

import dev.harolds.reservy.booking.dto.request.CreateBookingDTO;
import dev.harolds.reservy.booking.dto.response.BookingCreatedDTO;
import dev.harolds.reservy.booking.dto.response.BookingDTO;
import dev.harolds.reservy.booking.service.BookingService;
import dev.harolds.reservy.common.dto.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "api/v1/booking" )
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping(value = "{restaurantId}")
    public ResponseEntity<?> getBookingByRestaurantId(@PathVariable() Long restaurantId) {
        List<BookingDTO> response = bookingService.findBookingByRestaurantId(restaurantId);

        if (response.isEmpty()) return new ResponseEntity<>(
                new GeneralResponse<Object>("No se ha encontrado reservas para el restaurante"),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(
                new GeneralResponse<List<BookingDTO>>("Consulta realizada satisfactoriamente", response),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody CreateBookingDTO create) {
        BookingCreatedDTO saved = bookingService.saveBooking(create);
        if (saved == null) return new ResponseEntity<>(
                new GeneralResponse<Object>("El restaurante no existe"),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(
                new GeneralResponse<BookingCreatedDTO>("La reserva se ha realizado satisfactoriamente", saved),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deteleBooking( @PathVariable Long id ) {
        try {
            boolean deleted = bookingService.deleteBookingById(id);

            if (!deleted) return new ResponseEntity<>(
                    new GeneralResponse<Object>("La reserva no existe"),
                    HttpStatus.NOT_FOUND
            );

            return new ResponseEntity<>(
                    new GeneralResponse<Object>("Reserva eliminada satisfactoriamente"),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GeneralResponse<Object>("Ha ocurrido un error mientras se intentaba borrar la reserva"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
