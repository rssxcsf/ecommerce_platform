package com.example.shop.mapper;
import com.example.shop.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("SELECT * FROM goods")
    List<Goods> findAll();

    @Select("SELECT * FROM goods WHERE id = #{id}")
    Goods findById(Integer id);
}