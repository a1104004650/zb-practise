package com.zbc.practise.api.dto;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SearchDTO {

    private Map<String, String> exactMatches = new HashMap<>();
    private Map<String, String> fuzzyMatches = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private double latitude = 0;
    private double longitude = 0;
    // 半径，用于位置搜索
    private int radius = 0;

    public void setLocation(double latitude, double longitude, int radius) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    /**
     * 精准匹配
     * @param field 字段
     * @param value 值
     */
    public void addExactMatch(String field, String value) {
        exactMatches.put(field, value);
    }

    /**
     * 模糊匹配增加
     * @param field 字段
     * @param value 值
     */
    public void addFuzzyMatch(String field, String value) {
        fuzzyMatches.put(field, value);
    }

    @Override
    public String toString() {
        return "SearchDTO{" +
                "exactMatches=" + exactMatches +
                ", fuzzyMatches=" + fuzzyMatches +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", radius=" + radius +
                '}';
    }

    public Map<String, String> getExactMatches() {
        return exactMatches;
    }

    public void setExactMatches(Map<String, String> exactMatches) {
        this.exactMatches = exactMatches;
    }

    public Map<String, String> getFuzzyMatches() {
        return fuzzyMatches;
    }

    public void setFuzzyMatches(Map<String, String> fuzzyMatches) {
        this.fuzzyMatches = fuzzyMatches;
    }

    public static DateTimeFormatter getDateFormat() {
        return DATE_FORMAT;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
