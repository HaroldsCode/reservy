package dev.harolds.reservy.restaurant.service;

import dev.harolds.reservy.restaurant.dto.request.CreateRestaurantDTO;
import dev.harolds.reservy.restaurant.dto.request.UpdateRestaurantDTO;
import dev.harolds.reservy.restaurant.dto.response.RestaurantDTO;

import java.util.List;

public interface RestaurantService {

    public RestaurantDTO findRestaurantById(Long restaurantId );

    public List<RestaurantDTO> findRestaurantByCity( String city );

    public List<RestaurantDTO> findRestaurantByPartName( String partialName );

    public List<RestaurantDTO> findAllRestaurants();

    public RestaurantDTO saveRestaurant( CreateRestaurantDTO restaurantToSave );

    public boolean updateRestaurantAvailableTables( Long id, UpdateRestaurantDTO restaurantToUpdate );

    public boolean deleteRestaurantById( Long id );
}
