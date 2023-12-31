package dev.harolds.reservy.restaurant.service;

public interface RestaurantService {
    public Object findRestaurantByCity( String city );

    public Object findRestaurantByPartName( String partialName );

    public Object findAllRestaurants();

    public Object saveRestaurant( Object restaurantToSave );

    public Object updateRestaurantData( Object restaurantToUpdate );

    public Object deleteRestaurantById( Long id );
}
