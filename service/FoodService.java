package com.foodDelivery.FoodDelivery.service;

import com.foodDelivery.FoodDelivery.request.CreateFoodRequest;
import com.foodDelivery.FoodDelivery.entity.Category;
import com.foodDelivery.FoodDelivery.entity.Food;
import com.foodDelivery.FoodDelivery.entity.Restaurant;

import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant);

    void deleteFood(long foodId) throws Exception;

    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain,
                                         boolean isNonveg, boolean isSeasonal,
                                         String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws Exception;
}
