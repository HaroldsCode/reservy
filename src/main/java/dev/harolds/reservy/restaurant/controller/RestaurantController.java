package dev.harolds.reservy.restaurant.controller;

import dev.harolds.reservy.common.dto.GeneralResponse;
import dev.harolds.reservy.restaurant.dto.request.CreateRestaurantDTO;
import dev.harolds.reservy.restaurant.dto.request.UpdateRestaurantDTO;
import dev.harolds.reservy.restaurant.dto.response.RestaurantDTO;
import dev.harolds.reservy.restaurant.service.RestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping( "api/v1/restaurant" )
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value = {"", "/"})
    public ResponseEntity<?> getAllRestaurants() {
        List<RestaurantDTO> response = restaurantService.findAllRestaurants();
        return new ResponseEntity<>(
                new GeneralResponse<List<RestaurantDTO>>("Consulta realizada satisfactoriamente", response),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "city/{city}")
    public ResponseEntity<?> getRestaurantsByCity(@PathVariable() String city) {
        List<RestaurantDTO> response = restaurantService.findRestaurantByCity(city);

        if (response.isEmpty()) return new ResponseEntity<>(
                new GeneralResponse<Object>("No existen restaurantes en la ciudad "+ city.toLowerCase(Locale.ROOT)),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(
                new GeneralResponse<List<RestaurantDTO>>("Consulta realizada satisfactoriamente", response),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "search/{part}")
    public ResponseEntity<?> getRestaurantsByPartName(@PathVariable() String part) {
        List<RestaurantDTO> response = restaurantService.findRestaurantByPartName(part);

        if (response.isEmpty()) return new ResponseEntity<>(
                new GeneralResponse<Object>("No existen restaurantes con la palabra "+ part.toLowerCase(Locale.ROOT)),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(
                new GeneralResponse<List<RestaurantDTO>>("Consulta realizada satisfactoriamente", response),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody CreateRestaurantDTO create) {
        try {
            RestaurantDTO saved = restaurantService.saveRestaurant(create);
            if (saved == null) return new ResponseEntity<>(
                    new GeneralResponse<Object>("No se pudo dar de alta el restaurante."),
                    HttpStatus.BAD_REQUEST
            );

            return new ResponseEntity<>(
                    new GeneralResponse<RestaurantDTO>("El restaurante se ha dado de alta satisfactoriamente", saved),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GeneralResponse<Object>(e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id, @RequestBody UpdateRestaurantDTO update) {
        try {
            boolean updated = restaurantService.updateRestaurantAvailableTables(id, update);

            if (!updated) return new ResponseEntity<>(
                    new GeneralResponse<Object>("El restaurant no existe"),
                    HttpStatus.NOT_FOUND
            );

            return new ResponseEntity<>(
                    new GeneralResponse<Object>("El restaurante fue modificado satisfactoriamente"),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GeneralResponse<Object>(e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteRestaurant(@PathVariable Long id) {
        try {
            boolean deleted = restaurantService.deleteRestaurantById(id);

            if (!deleted) return new ResponseEntity<>(
                    new GeneralResponse<Object>("El restaurant no existe no existe"),
                    HttpStatus.NOT_FOUND
            );

            return new ResponseEntity<>(
                    new GeneralResponse<Object>("El restaurante fue eliminado satisfactoriamente"),
                    HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new GeneralResponse<Object>("Ha ocurrido un error mientras se intentaba borrar un restaurante"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
