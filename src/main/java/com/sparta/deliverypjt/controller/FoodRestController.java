package com.sparta.deliverypjt.controller;

import com.sparta.deliverypjt.model.Food;
import com.sparta.deliverypjt.requestDto.FoodDto;
import com.sparta.deliverypjt.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodRestController {
    private final FoodService foodService;

    @Autowired
    public FoodRestController(FoodService foodService){
        this.foodService = foodService;
    }

    //음식점의 음식 등록
    @PostMapping("/restaurant/{restaurantId}/food/register")
    public void registerFood(@PathVariable Long restaurantId, @RequestBody List<FoodDto> requestDto){
        foodService.registerFood(restaurantId, requestDto);
    }

    //음식점 전체 메뉴 조회
    @GetMapping("/restaurant/{restaurantId}/foods")
    public List<Food> foodList(@PathVariable Long restaurantId){
        return foodService.foodList(restaurantId);
    }
}
