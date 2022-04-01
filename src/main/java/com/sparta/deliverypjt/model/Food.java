package com.sparta.deliverypjt.model;

import com.sparta.deliverypjt.requestDto.FoodDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor
@SequenceGenerator(name = "Food_SEQ", sequenceName = "Food_SEQ_NO", allocationSize = 1)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Food_SEQ")
    private Long id;

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    public Food(FoodDto requestDto){
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.restaurantId = requestDto.getRestaurantId();
    }
}
