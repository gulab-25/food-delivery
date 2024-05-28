package com.foodDelivery.FoodDelivery.service;

import com.foodDelivery.FoodDelivery.request.CreateRestaurantRequest;
import com.foodDelivery.FoodDelivery.entity.Restaurant;

import java.util.List;

public interface RestaurantService {
    public Restaurant createRestaurant(CreateRestaurantRequest req);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

//    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

//    public RestaurantDto addToFavorites(Long restaurantId,User user) throws Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;

}
