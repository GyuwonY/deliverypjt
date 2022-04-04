package com.sparta.deliverypjt.requestDto;

import lombok.*;

@Getter
@Setter
@ToString
public class ReataurantDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
    private int x;
    private int y;
}
