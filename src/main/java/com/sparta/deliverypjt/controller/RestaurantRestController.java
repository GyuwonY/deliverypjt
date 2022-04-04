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

    //배달 가능 음식점 조회
    @GetMapping("/restaurants")
    public List<Restaurant> canDeliveryRestaurantList(@RequestParam(value = "x")int x, @RequestParam(value = "y")int y){
        return restaurantService.canDeliveryRestaurantList(x, y);
    }
}
