package com.sparta.deliverypjt.requestDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOrderDto {
    Long orderId;
    String name;
    int quantity;
    int price;
}