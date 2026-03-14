package com.example.shop.service;

import com.example.shop.entity.Goods;
import com.example.shop.entity.Orders;
import com.example.shop.mapper.GoodsMapper;
import com.example.shop.mapper.OrdersMapper;
import com.example.shop.util.PearsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class RecommendService {

    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    public List<Goods> recommend(Integer userId) {
        // 1. 准备数据: Map<UserId, Map<GoodsId, Score>>
        List<Orders> orders = ordersMapper.findAllOrders();
        Map<Integer, Map<Integer, Integer>> userScores = new HashMap<>();

        for (Orders o : orders) {
            userScores.putIfAbsent(o.getUserId(), new HashMap<>());
            // 论文逻辑：购买权重设为 5
            userScores.get(o.getUserId()).merge(o.getGoodsId(), 5, Integer::sum);
        }

        if (!userScores.containsKey(userId)) {
            return goodsMapper.findAll().subList(0, 5); // 冷启动
        }

        // 2. 找最相似的用户
        Map<Integer, Integer> current = userScores.get(userId);
        double maxSim = 0;
        Integer bestFriendId = null;

        for (Integer otherId : userScores.keySet()) {
            if (otherId.equals(userId)) continue;
            double sim = PearsonUtil.calculate(current, userScores.get(otherId));
            if (sim > maxSim) { maxSim = sim; bestFriendId = otherId; }
        }

        // 3. 生成推荐
        List<Goods> result = new ArrayList<>();
        if (bestFriendId != null) {
            Map<Integer, Integer> friend = userScores.get(bestFriendId);
            for (Integer gid : friend.keySet()) {
                if (!current.containsKey(gid) && result.size() < 5) {
                    result.add(goodsMapper.findById(gid));
                }
            }
        }

        // 4. 不足补热门
        if (result.size() < 5) {
            for (Goods g : goodsMapper.findAll()) {
                if (result.size() >= 5) break;
                if (!result.contains(g)) result.add(g);
            }
        }
        return result;
    }
}