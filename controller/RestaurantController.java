package com.foodDelivery.FoodDelivery.controller;

import com.foodDelivery.FoodDelivery.entity.Restaurant;
import com.foodDelivery.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search/{id}")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@PathVariable String keyword) throws Exception{
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long id) throws Exception{
        Restaurant restaurantById = restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurantById, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurant() throws Exception{
        List<Restaurant> allRestaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(allRestaurant, HttpStatus.OK);
    }

//    @PutMapping("/{id}/add-favorites")
//    public ResponseEntity<RestaurantDto> addToFavorites(@PathVariable Long id) throws Exception{
//
//        RestaurantDto  restaurantDto = restaurantService.addToFavorites(id, user);
//        return new ResponseEntity<>(restaurantById, HttpStatus.OK);
//    }
}
