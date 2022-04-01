package com.sparta.deliverypjt.requestDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FoodDto {
    private String name;
    private int price;
    private Long restaurantId;
}
