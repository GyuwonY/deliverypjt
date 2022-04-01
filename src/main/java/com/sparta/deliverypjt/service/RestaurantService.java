package com.sparta.deliverypjt.service;

import com.sparta.deliverypjt.model.Food;
import com.sparta.deliverypjt.model.Foods;
import com.sparta.deliverypjt.model.Restaurant;
import com.sparta.deliverypjt.repository.FoodRepository;
import com.sparta.deliverypjt.repository.RestaurantRepository;
import com.sparta.deliverypjt.requestDto.FoodDto;
import com.sparta.deliverypjt.requestDto.ReataurantDto;
import com.sparta.deliverypjt.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, FoodRepository foodRepository){
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
    }

    //전체 음식점 조회
    public List<Restaurant> restaurantList() {
        return restaurantRepository.findAllByOrderByIdAsc();
    }

    //음식점 등록
    public Restaurant registerRestaurant(ReataurantDto requestDto) {
        // 최소 주문가격 검사
        Validator.minOrderPriceValidator(requestDto.getMinOrderPrice());
        // 배달비 검사
        Validator.deliveryFeeValidator(requestDto.getDeliveryFee());

        Restaurant restaurant = new Restaurant(requestDto);
        return restaurantRepository.save(restaurant);
    }

    @Transactional(rollbackOn = IllegalArgumentException.class)
    public void registerFood(Long restaurantId, List<FoodDto> requestDto) {

        for (FoodDto dto : requestDto) {
            Optional<Food> foundResult = foodRepository.findByRestaurantIdAndName(restaurantId, dto.getName());

            if (foundResult.isPresent()) {
                throw new IllegalArgumentException("중복 메뉴 존재");
            }

            //가격 검사
            Validator.priceValidator(dto.getPrice());

            dto.setRestaurantId(restaurantId);

            Food food = new Food(dto);

            foodRepository.save(food);
        }

    }

    public List<Food> foodList(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
