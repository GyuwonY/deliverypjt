package com.sparta.deliverypjt.repository;

import com.sparta.deliverypjt.model.Foods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodsRepository extends JpaRepository<Foods, Long> {
}
