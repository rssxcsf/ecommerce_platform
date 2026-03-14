package com.example.shop.util;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class PearsonUtil {
    public static double calculate(Map<Integer, Integer> user1, Map<Integer, Integer> user2) {
        Set<Integer> common = user1.keySet().stream().filter(user2::containsKey).collect(Collectors.toSet());
        if (common.size() < 2) return 0.0;

        int n = common.size();
        double sumX=0, sumY=0, sumXY=0, sumX2=0, sumY2=0;
        for (Integer id : common) {
            int x = user1.get(id);
            int y = user2.get(id);
            sumX += x; sumY += y; sumXY += x*y; sumX2 += x*x; sumY2 += y*y;
        }
        double numerator = sumXY - (sumX * sumY) / n;
        double denominator = Math.sqrt((sumX2 - (sumX*sumX)/n) * (sumY2 - (sumY*sumY)/n));
        return denominator == 0 ? 0 : numerator / denominator;
    }
}