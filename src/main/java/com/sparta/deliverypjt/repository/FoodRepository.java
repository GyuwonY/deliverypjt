package com.sparta.deliverypjt.repository;

import com.sparta.deliverypjt.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findAllByRestaurantId(Long restaurantId);
    Optional<Food> findByRestaurantIdAndName(Long restaurantId, String name);
}
