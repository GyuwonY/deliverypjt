package com.sparta.deliverypjt.controller;

import com.sparta.deliverypjt.model.Order;
import com.sparta.deliverypjt.requestDto.OrderRequestDto;
import com.sparta.deliverypjt.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderRestController {
    private final OrderService orderService;

    @Autowired
    public OrderRestController(OrderService orderService){
        this.orderService = orderService;
    }

    // 주문 접수
    @PostMapping("/order/request")
    public Order order(@RequestParam(value = "x")int x, @RequestParam(value = "y")int y, @RequestBody OrderRequestDto orderRequestDto){
        return orderService.order(orderRequestDto, x, y);
    }

    // 주문 조회
    @GetMapping("/orders")
    public List<Order> findOrder(){
        return orderService.findOrder();
    }
}
