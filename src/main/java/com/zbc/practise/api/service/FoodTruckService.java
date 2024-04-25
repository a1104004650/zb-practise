package com.zbc.practise.api.service;


import com.zbc.practise.api.cache.FoodTruckCache;
import com.zbc.practise.api.dto.FoodTruck;
import com.zbc.practise.api.dto.SearchDTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 快餐车业务处理类
 */
public class FoodTruckService {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");

    // 地球半径常量
    private static final double EARTH_RADIUS = 6371.0;

    /**
     * 查询条件，根据条件查询
     * @param searchDTO
     * @return
     */
    public static List<FoodTruck> executeQuery(SearchDTO searchDTO) {
        return FoodTruckCache.getFoodTrucks().stream()
                .filter(truck -> matchesExact(searchDTO.getExactMatches(), truck))
                .filter(truck -> matchesFuzzy(searchDTO.getFuzzyMatches(), truck))
                .filter(truck -> matchesLocation(searchDTO, truck))
                .collect(Collectors.toList());
    }

    private static boolean matchesExact(Map<String, String> exactMatches, FoodTruck truck) {
        return exactMatches.entrySet().stream()
                .allMatch(entry -> truck.getField(entry.getKey()).equals(entry.getValue()));
    }

    private static boolean matchesFuzzy(Map<String, String> fuzzyMatches, FoodTruck truck) {
        return fuzzyMatches.entrySet().stream()
                .allMatch(entry -> truck.getField(entry.getKey()).toLowerCase().contains(entry.getValue().toLowerCase()));
    }

    private static boolean matchesLocation(SearchDTO searchDTO, FoodTruck truck) {
        if (searchDTO.getRadius() > 0) {
            double distance = calculateDistance(searchDTO.getLatitude(), searchDTO.getLongitude(), truck.getLatitude(), truck.getLongitude());
            return distance <= searchDTO.getRadius();
        }
        return true; // 如果没有提供位置信息，则默认为匹配
    }

    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // 将纬度之差和经度之差转换为弧度
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        // 使用Haversine公式计算球面上两点之间的距离
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        // 返回结果是两点之间的距离，单位是千米（km）
        return EARTH_RADIUS * c;
    }


    // 是否批准 可选
    private static boolean isValid(FoodTruck truck) {
        LocalDate now = LocalDate.now();
        LocalDate approvedDate = LocalDate.parse(truck.getApproved(), DATE_FORMAT);
        LocalDate expirationDate = LocalDate.parse(truck.getExpirationDate(), DATE_FORMAT);
        return truck.getStatus().equalsIgnoreCase("APPROVED") &&
                (now.isAfter(approvedDate) || now.isEqual(approvedDate)) &&
                now.isBefore(expirationDate);
    }
}
