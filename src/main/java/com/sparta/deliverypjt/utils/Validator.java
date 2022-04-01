package com.sparta.deliverypjt.utils;

import org.springframework.stereotype.Component;

@Component
public class Validator {
    public static void minOrderPriceValidator(int minOrderPrice){
        if(minOrderPrice > 100000 || minOrderPrice < 1000){
            throw new IllegalArgumentException("최소 주문금액 범위 초과");
        }
        if(minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("최소 주문금액 단위 오류");
        }
    }

    public static void deliveryFeeValidator(int deliveryFee) {
        if(deliveryFee < 0 || deliveryFee > 10000){
            throw new IllegalArgumentException("배달비 범위 초과");
        }
        if(deliveryFee % 500 != 0){
            throw new IllegalArgumentException("배달비 단위 오류");
        }
    }

    public static void priceValidator(int price){
        if(price < 100 || price > 1000000){
            throw new IllegalArgumentException("가격 범위 초과");
        }
        if(price % 100 != 0){
            throw new IllegalArgumentException("가격 단위 오류");
        }
    }
}
