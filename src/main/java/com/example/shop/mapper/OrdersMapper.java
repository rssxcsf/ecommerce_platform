package com.example.shop.mapper;
import com.example.shop.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface OrdersMapper {
    @Select("SELECT * FROM orders WHERE status = '已完成'")
    List<Orders> findAllOrders();
}