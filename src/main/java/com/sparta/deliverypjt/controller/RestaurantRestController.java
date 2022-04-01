package com.sparta.deliverypjt.controller;

import com.sparta.deliverypjt.model.Food;
import com.sparta.deliverypjt.model.Restaurant;
import com.sparta.deliverypjt.requestDto.FoodDto;
import com.sparta.deliverypjt.requestDto.ReataurantDto;
import com.sparta.deliverypjt.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantRestController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    //전체 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> restaurantList(){
        return restaurantService.restaurantList();
    }

    //음식점 등록
    @PostMapping("/restaurant/register")
    public Restaurant registerRestaurant(@RequestBody ReataurantDto requestDto){
        return restaurantService.registerRestaurant(requestDto);
    }

    //음식점의 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> requestDto){
        restaurantService.registerFood(restaurantId, requestDto);
    }

    //음식점 전체 메뉴 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> foodList(@PathVariable Long restaurantId){
        return restaurantService.foodList(restaurantId);
    }
}
