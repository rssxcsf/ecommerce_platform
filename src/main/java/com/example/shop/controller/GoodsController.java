package com.example.shop.controller;

import com.example.shop.common.Result;
import com.example.shop.entity.Goods;
import com.example.shop.mapper.GoodsMapper;
import com.example.shop.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods")
@CrossOrigin // 允许跨域
public class GoodsController {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private RecommendService recommendService;

    @GetMapping("/list")
    public Result list() {
        return Result.success(goodsMapper.findAll());
    }

    // 论文亮点接口：基于用户ID推荐
    // 测试：浏览器访问 http://localhost:8081/goods/recommend/1
    @GetMapping("/recommend/{userId}")
    public Result recommend(@PathVariable Integer userId) {
        return Result.success(recommendService.recommend(userId));
    }
}