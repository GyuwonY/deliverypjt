package com.sparta.deliverypjt.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@Entity
@NoArgsConstructor
@SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ_NO", allocationSize = 1)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    private Long id;


}
