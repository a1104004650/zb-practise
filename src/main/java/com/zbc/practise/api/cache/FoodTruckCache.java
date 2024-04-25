package com.zbc.practise.api.cache;


import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.zbc.practise.api.dto.FoodTruck;
import com.zbc.practise.api.dto.SearchDTO;
import com.zbc.practise.api.service.FoodTruckService;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存类
 * @author
 */
public class FoodTruckCache {

    private static List<FoodTruck> foodTrucksCache = new ArrayList<>();
    private static final Map<String, List<FoodTruck>> trucksSearchCache = new ConcurrentHashMap<>();

    // loadFoodTrucks 初始化init
    public static void loadFoodTrucks(){
        try (Reader reader = Files.newBufferedReader(Paths.get(ClassLoader.getSystemResource("data.csv").toURI()))) {
            CsvToBean<FoodTruck> csvToBean = new CsvToBeanBuilder<FoodTruck>(reader)
                    .withType(FoodTruck.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            foodTrucksCache = csvToBean.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 先查询，再放入缓存
     * 可以考虑使用caffeine
     * @param searchDTO
     * @return
     */
    public static List<FoodTruck> getFoodTrucksByCache(SearchDTO searchDTO){
        List<FoodTruck> foodTrucks = FoodTruckService.executeQuery(searchDTO);
        // 缓存
        trucksSearchCache.put(searchDTO.toString(), foodTrucks);
        return foodTrucks;
    }

    public static List<FoodTruck> getFoodTrucks(){
        return foodTrucksCache;
    }



}
