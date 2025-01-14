package com.foodDelivery.FoodDelivery.service.impl;

import com.foodDelivery.FoodDelivery.entity.Category;
import com.foodDelivery.FoodDelivery.entity.Food;
import com.foodDelivery.FoodDelivery.entity.Restaurant;
import com.foodDelivery.FoodDelivery.repository.FoodRepository;
import com.foodDelivery.FoodDelivery.request.CreateFoodRequest;
import com.foodDelivery.FoodDelivery.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;




    @Override
    public Food createFood(CreateFoodRequest req, Category category, Restaurant restaurant) {
        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestaurant(restaurant);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setIngredientsItems(req.getIngredients());
        food.setPrice(req.getPrice());
        food.setSeasonal(req.isSeasional());
        food.setVegitarian(req.isVegetarin());

        Food saveFood = foodRepository.save(food);

        restaurant.getFoods().add(saveFood);
        return saveFood;
    }

    @Override
    public void deleteFood(long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);

    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarain,
                                         boolean isNonveg, boolean isSeasonal, String foodCategory) {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if(isVegitarain){
            foods = filterByVegetarian(foods,isVegitarain);
        }
        if(isNonveg){
            foods = filterByNonveg(foods,isNonveg);
        }

        if(isSeasonal){
            foods = filterBySeasonal(foods,isSeasonal);
        }

        if(foodCategory!=null  && !foodCategory.equals("")){
            foods = filterByCategory(foods,foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if (food.getFoodCategory()!=null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream().filter(food -> food.isVegitarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegitarain) {
        return foods.stream().filter(food -> food.isVegitarian()==isVegitarain).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);

        if(optionalFood.isEmpty()){
            throw new Exception("Food not exist");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {

        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);

    }
}
