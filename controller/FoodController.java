package com.foodDelivery.FoodDelivery.controller;

import com.foodDelivery.FoodDelivery.entity.Food;
import com.foodDelivery.FoodDelivery.entity.Restaurant;
import com.foodDelivery.FoodDelivery.request.CreateFoodRequest;
import com.foodDelivery.FoodDelivery.service.FoodService;
import com.foodDelivery.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> SearchFood(@RequestParam String name) throws Exception {

        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@RequestParam boolean vegetarian,
                                                  @RequestParam boolean seasonal,
                                                  @RequestParam boolean nonveg,
                                                  @RequestParam(required = false) String food_category,
                                                  @PathVariable Long restaurantId) throws Exception {

        List<Food> restaurantsFood = foodService.getRestaurantsFood(restaurantId, vegetarian, nonveg, seasonal, food_category);
        return new ResponseEntity<>(restaurantsFood, HttpStatus.OK);
    }

}
