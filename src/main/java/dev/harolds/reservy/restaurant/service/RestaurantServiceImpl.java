package dev.harolds.reservy.restaurant.service;

import dev.harolds.reservy.restaurant.dto.request.CreateRestaurantDTO;
import dev.harolds.reservy.restaurant.dto.request.UpdateRestaurantDTO;
import dev.harolds.reservy.restaurant.dto.response.RestaurantDTO;
import dev.harolds.reservy.restaurant.entity.Restaurant;
import dev.harolds.reservy.restaurant.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public RestaurantDTO findRestaurantById(Long restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);

        if (optionalRestaurant.isEmpty() ) return null;

        Restaurant restaurant = optionalRestaurant.get();

        return RestaurantDTO.builder()
            .id(restaurant.getId())
            .nit(restaurant.getNit())
            .name(restaurant.getName())
            .address(restaurant.getAddress())
            .city(restaurant.getCity())
            .tablesAvailable(restaurant.getTablesAvailable())
            .build();
    }

    @Override
    public List<RestaurantDTO> findRestaurantByCity(String city) {
        List<Restaurant> result = restaurantRepository.findRestaurantsByCity(city);

        return result
                .stream()
                .map( restaurant -> RestaurantDTO.builder()
                        .id(restaurant.getId())
                        .nit(restaurant.getNit())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .city(restaurant.getCity())
                        .tablesAvailable(restaurant.getTablesAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantDTO> findRestaurantByPartName(String partialName) {
        List<Restaurant> result = restaurantRepository.findAllRestaurantsByNameLike(partialName);

        return result
                .stream()
                .map( restaurant -> RestaurantDTO.builder()
                        .id(restaurant.getId())
                        .nit(restaurant.getNit())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .city(restaurant.getCity())
                        .tablesAvailable(restaurant.getTablesAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> result = restaurantRepository.findAll();

        return result
                .stream()
                .map( restaurant -> RestaurantDTO.builder()
                        .id(restaurant.getId())
                        .nit(restaurant.getNit())
                        .name(restaurant.getName())
                        .address(restaurant.getAddress())
                        .city(restaurant.getCity())
                        .tablesAvailable(restaurant.getTablesAvailable())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantDTO saveRestaurant(CreateRestaurantDTO restaurantToSave) {
        Restaurant restaurant = restaurantRepository.save(Restaurant.builder()
            .nit(restaurantToSave.getNit())
            .name(restaurantToSave.getName())
            .address(restaurantToSave.getAddress())
            .city(restaurantToSave.getCity().toLowerCase(Locale.ROOT))
            .tablesAvailable(restaurantToSave.getTablesAvailable())
            .build());

        return RestaurantDTO.builder()
            .id(restaurant.getId())
            .nit(restaurantToSave.getNit())
            .name(restaurantToSave.getName())
            .address(restaurantToSave.getAddress())
            .city(restaurantToSave.getCity())
            .tablesAvailable(restaurantToSave.getTablesAvailable())
            .build();
    }

    @Override
    public boolean updateRestaurantAvailableTables( Long id, UpdateRestaurantDTO restaurantToUpdate ) {
        if ( restaurantRepository.findById(id).isEmpty() ) return false;
        restaurantRepository.updateTablesAvailable( id, restaurantToUpdate.getTablesAvailable() );
        return true;
    }

    @Override
    public boolean deleteRestaurantById(Long id) {
        if ( restaurantRepository.findById(id).isEmpty() ) return false;
        restaurantRepository.deleteById(id);
        return true;
    }
}