package com.example.shop.entity;
import lombok.Data;
@Data
public class Goods {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private Integer count;
}