package com.foodDelivery.FoodDelivery.controller;

import com.foodDelivery.FoodDelivery.request.CreateRestaurantRequest;
import com.foodDelivery.FoodDelivery.request.MessageResponse;
import com.foodDelivery.FoodDelivery.entity.Restaurant;
import com.foodDelivery.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/restaurants")
public class AdminRestaurantController {

    @Autowired
    private RestaurantService restaurantService;

//    @Autowired
//    private UserService userService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody CreateRestaurantRequest req) throws Exception{
        Restaurant restaurant = restaurantService.createRestaurant(req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody CreateRestaurantRequest req,
                                                       @PathVariable Long id) throws Exception{
        Restaurant restaurant = restaurantService.updateRestaurant(id,req);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteRestaurant(@PathVariable Long id) throws Exception{
        restaurantService.deleteRestaurant(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Restaurant Deleted Successfully");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurant() throws Exception{
        List<Restaurant> allRestaurant = restaurantService.getAllRestaurant();
        return new ResponseEntity<>(allRestaurant, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@PathVariable String keyword) throws Exception{
        List<Restaurant> restaurants = restaurantService.searchRestaurant(keyword);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

//    @GetMapping("/user")
//    public ResponseEntity<Restaurant> getRestaurantByUserId(@PathVariable Long userId) throws Exception{
//        Restaurant restaurantByUserId = restaurantService.getRestaurantByUserId(userId);
//        return new ResponseEntity<>(restaurantByUserId, HttpStatus.OK);
//    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable Long id) throws Exception{
        Restaurant restaurant = restaurantService.updateRestaurantStatus(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
