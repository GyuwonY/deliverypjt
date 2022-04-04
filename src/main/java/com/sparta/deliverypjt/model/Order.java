package com.sparta.deliverypjt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private int additionalDeliveryFee;

    @Column(nullable = false)
    private int totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "tbl_order_id")
    List<Foods> foods = new ArrayList<>();

    public Order(String restaurantName, int deliveryFee, int additionalDeliveryFee){
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.additionalDeliveryFee = additionalDeliveryFee;
        this.totalPrice = deliveryFee + additionalDeliveryFee;
    }

    public void totalPrice(int price){
        this.totalPrice += price;
    }
}
