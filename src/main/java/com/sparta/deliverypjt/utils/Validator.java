package com.sparta.deliverypjt.utils;

import com.sparta.deliverypjt.requestDto.FoodOrderRequestDto;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    public static void minOrderPriceValidator(int minOrderPrice){
        if(minOrderPrice > 100000 || minOrderPrice < 1000){
            throw new IllegalArgumentException("최소 주문금액 범위 오류");
        }
        if(minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("최소 주문금액 단위 오류");
        }
    }

    public static void deliveryFeeValidator(int deliveryFee) {
        if(deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalArgumentException("배달비 범위 오류");
        }
        if(deliveryFee % 500 != 0){
            throw new IllegalArgumentException("배달비 단위 오류");
        }
    }

    public static void priceValidator(int price){
        if(price < 100 || price > 1000000){
            throw new IllegalArgumentException("가격 범위 오류");
        }
        if(price % 100 != 0){
            throw new IllegalArgumentException("가격 단위 오류");
        }
    }

    public static void orderValidator(int quantity) {
        if(quantity > 100 || quantity < 1){
            throw new IllegalArgumentException("수량 범위 오류");
        }
    }


    public static void minOrderValidator(int orderPrice, int minOrderPrice) {
        if(orderPrice < minOrderPrice){
            throw new IllegalArgumentException("최소 주문금액 미달 오류");
        }
    }
}
