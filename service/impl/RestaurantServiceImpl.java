package com.foodDelivery.FoodDelivery.service.impl;

import com.foodDelivery.FoodDelivery.request.CreateRestaurantRequest;
import com.foodDelivery.FoodDelivery.entity.Address;
import com.foodDelivery.FoodDelivery.entity.Restaurant;
import com.foodDelivery.FoodDelivery.repository.AddressRepository;
import com.foodDelivery.FoodDelivery.repository.RestaurantRepository;
import com.foodDelivery.FoodDelivery.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

//    @Autowired
//    private UserRepository UserRepository;

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req) {

        Address address = addressRepository.save(req.getAddress());
//        Address address = req.getAddress();
        Restaurant restaurant = new Restaurant();
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setOpen(req.isOpen());
        restaurant.setRegistrationDate(LocalDateTime.now());
     //   restaurant.setOwner(user);
        restaurant.setAddress(address);
 //       address.setRestaurant(restaurant);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType()!=null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }
        if(restaurant.getDescription()!=null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }
        if(restaurant.getName()!=null){
            restaurant.setName(updatedRestaurant.getName());
        }

        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<Restaurant> getAllRestaurant() {

        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> opt = restaurantRepository.findById(id);
        if(opt.isEmpty()){
            throw new Exception("restaurant is not found with this id"+id);
        }
        return opt.get();
    }

//    @Override
//    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
//
//        Restaurant restaurant = restaurantRepository.findByOwnerId(userId);
//        if(restaurant==null){
//            throw new Exception("restaurant not found with ownerid"+userId);
//        }
//        return restaurant;
//    }

//    @Override
//    public RestaurantDto addToFavorites(Long restaurantId, User user) throws Exception {
//        Restaurant restaurant = findRestaurantById(restaurantId);
//        RestaurantDto dto = new RestaurantDto();
//        dto.setDiscription(restaurant.getDescription());
//        dto.setImaes(restaurant.getImages());
//        dto.setTitle(restaurant.getName());
//        dto.setId(restaurantId);
//
//        if(user.getFavorites().contains(dto)){
//            user.getFavorites().remove(dto);
//        }
//        else user.getFavorites().add(dto);
//        userRepository.save(user);
//        return dto;
//    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {

       Restaurant restaurant = findRestaurantById(id);
       restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
