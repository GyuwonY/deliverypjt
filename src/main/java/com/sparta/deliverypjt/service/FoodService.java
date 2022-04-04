package com.sparta.deliverypjt.service;

import com.sparta.deliverypjt.model.Food;
import com.sparta.deliverypjt.repository.FoodRepository;
import com.sparta.deliverypjt.requestDto.FoodDto;
import com.sparta.deliverypjt.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository){
        this.foodRepository = foodRepository;
    }


    // 음식 등록
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


    // 음식 리스트
    public List<Food> foodList(Long restaurantId) {
        return foodRepository.findAllByRestaurantId(restaurantId);
    }
}
