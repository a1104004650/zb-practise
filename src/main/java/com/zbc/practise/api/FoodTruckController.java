package com.zbc.practise.api;


import com.zbc.practise.api.cache.FoodTruckCache;
import com.zbc.practise.api.dto.FoodTruck;
import com.zbc.practise.api.dto.SearchDTO;

import java.util.List;

/**
 * 雅诗兰黛git面试题
 */
public class FoodTruckController {

    public static void main(String[] args) {
        // init
        FoodTruckCache.loadFoodTrucks();

        // 条件可选
        SearchDTO query = new SearchDTO();
        query.addFuzzyMatch("applicant", "BH & MT LLC");
        //query.addFuzzyMatch("locationDescription", "BRODERICK");
        query.addExactMatch("facilityType", "Truck");
        query.setLocation(37.7749, -122.4194, 5);  // 以旧金山为例，半径5公里内
        List<FoodTruck> results = FoodTruckCache.getFoodTrucksByCache(query);

        System.out.println(results);
    }

}
