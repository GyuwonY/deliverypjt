package com.sparta.deliverypjt.service;

import com.sparta.deliverypjt.model.Food;
import com.sparta.deliverypjt.model.Foods;
import com.sparta.deliverypjt.model.Order;
import com.sparta.deliverypjt.model.Restaurant;
import com.sparta.deliverypjt.repository.FoodRepository;
import com.sparta.deliverypjt.repository.FoodsRepository;
import com.sparta.deliverypjt.repository.OrderRepository;
import com.sparta.deliverypjt.repository.RestaurantRepository;
import com.sparta.deliverypjt.requestDto.FoodOrderRequestDto;
import com.sparta.deliverypjt.requestDto.OrderRequestDto;
import com.sparta.deliverypjt.utils.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final FoodsRepository foodsRepository;

    @Autowired
    public OrderService(RestaurantRepository restaurantRepository, FoodRepository foodRepository, OrderRepository orderRepository, FoodsRepository foodsRepository){
        this.restaurantRepository = restaurantRepository;
        this.foodRepository = foodRepository;
        this.orderRepository = orderRepository;
        this.foodsRepository = foodsRepository;
    }

    // 주문 접수
    @Transactional(rollbackOn = IllegalArgumentException.class)
    public Order order(OrderRequestDto orderRequestDto, int x, int y) {
        Long restaurantId = orderRequestDto.getRestaurantId();
        Restaurant restaurant = restaurantRepository.findById(
                restaurantId).orElseThrow(IllegalArgumentException::new);

        //추가 배달비
        int additionalDeliveryFee = Math.abs(restaurant.getX()+restaurant.getY()-x-y) * 500;

        //가게 이름, 배달비 등록
        Order order = new Order(restaurant.getName(), restaurant.getDeliveryFee(), additionalDeliveryFee);

        List<FoodOrderRequestDto> foods = orderRequestDto.getFoods();

        List<Foods> foodsList  = new ArrayList<>();
        for(FoodOrderRequestDto food : foods){

            // 주문 별 수량 검사
            Validator.orderValidator(food.getQuantity());

            Food menu = foodRepository.findById(food.getId()).orElseThrow(IllegalArgumentException::new);

            Foods orders = new Foods(menu.getName(), menu.getPrice(), food.getQuantity());
            order.totalPrice(orders.getPrice());
            foodsList.add(orders);
        }

        //최소 주문금액 검사
        Validator.minOrderValidator(order.getTotalPrice()-order.getDeliveryFee(), restaurant.getMinOrderPrice());

        order.setFoods(foodsList);
        return orderRepository.save(order);
    }

    // 주문 조회
    public List<Order> findOrder() {
        List<Order> order = orderRepository.findAll();
        System.out.println(order);
        return order;
    }
}
