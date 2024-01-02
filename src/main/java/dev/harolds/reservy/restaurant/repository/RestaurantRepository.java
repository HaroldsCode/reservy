package dev.harolds.reservy.restaurant.repository;

import dev.harolds.reservy.restaurant.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findRestaurantsByCity( String city );
    @Query("select r from Restaurant r where r.name like %:name%")
    List<Restaurant> findAllRestaurantsByNameLike( @Param(value = "name") String name );

    @Transactional
    @Modifying
    @Query("update Restaurant r set r.tablesAvailable = :availableTables where r.id = :restaurantId")
    void updateTablesAvailable( @Param(value = "restaurantId") Long restaurantId, @Param(value = "availableTables") Integer availableTables );
}