package dev.harolds.reservy.restaurant.repository;

import dev.harolds.reservy.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    public Restaurant findRestaurantById( Long restaurantId );

}