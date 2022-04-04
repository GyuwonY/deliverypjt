package com.sparta.deliverypjt.service;

import com.sparta.deliverypjt.model.Restaurant;
import com.sparta.deliverypjt.repository.RestaurantRepository;
import com.sparta.deliverypjt.requestDto.ReataurantDto;
import com.sparta.deliverypjt.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
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

    // 배달 가능 음식점 조회
    public List<Restaurant> canDeliveryRestaurantList(int x, int y) {
        List<Restaurant> restaurantList = new ArrayList<>();

        for(Restaurant restaurant : restaurantRepository.findAll()){
            if(Math.abs(restaurant.getX()+restaurant.getY()-x-y)<=3){
                restaurantList.add(restaurant);
            }
        }
        return  restaurantList;
    }
}