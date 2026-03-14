package com.example.shop.entity;

import lombok.Data;

@Data
public class Orders {
    // 订单表主键ID
    private Integer id;
    // 订单编号（唯一标识，如ORD001）
    private String orderId;
    // 商品ID
    private Integer goodsId;
    // 商家ID（可选，简化版可暂不使用）
    private Integer businessId;
    // 购买数量
    private Integer num;
    // 下单用户ID
    private Integer userId;
    // 订单金额
    private Double price;
    // 收货地址ID（可选）
    private Integer addressId;
    // 订单状态（待发货/已发货/已完成/已取消）
    private String status;
}