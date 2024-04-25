package com.zbc.practise.api.dto;

import com.opencsv.bean.CsvBindByName;

import java.lang.reflect.Field;

public class FoodTruck {

    @CsvBindByName(column = "locationid")
    private String locationId;
    @CsvBindByName(column = "Applicant")
    private String applicant;
    @CsvBindByName(column = "FacilityType")
    private String facilityType;
    @CsvBindByName(column = "cnn")
    private String cnn;
    @CsvBindByName(column = "LocationDescription")
    private String locationDescription;
    @CsvBindByName(column = "blocklot")
    private String blocklot;
    @CsvBindByName(column = "block")
    private String block;
    @CsvBindByName(column = "lot")
    private String lot;
    @CsvBindByName(column = "Address")
    private String address;
    @CsvBindByName(column = "permit")
    private String permit;
    @CsvBindByName(column = "Status")
    private String status;
    @CsvBindByName(column = "FoodItems")
    private String foodItems;
    @CsvBindByName(column = "Latitude")
    private double latitude;
    @CsvBindByName(column = "Longitude")
    private double longitude;
    @CsvBindByName(column = "Schedule")
    private String schedule;
    @CsvBindByName(column = "Approved")
    private String approved;
    @CsvBindByName(column = "Location")
    private String location;
    @CsvBindByName(column = "ExpirationDate")
    private String expirationDate;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(String foodItems) {
        this.foodItems = foodItems;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }


    public String getCnn() {
        return cnn;
    }

    public void setCnn(String cnn) {
        this.cnn = cnn;
    }

    public String getBlocklot() {
        return blocklot;
    }

    public void setBlocklot(String blocklot) {
        this.blocklot = blocklot;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // 根据字段名获取属性值的方法
    public String getField(String fieldName) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            Object value = field.get(this);

            return value.toString();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "FoodTruck{" +
                "locationId='" + locationId + '\'' +
                ", applicant='" + applicant + '\'' +
                ", facilityType='" + facilityType + '\'' +
                ", cnn='" + cnn + '\'' +
                ", locationDescription='" + locationDescription + '\'' +
                ", blocklot='" + blocklot + '\'' +
                ", block='" + block + '\'' +
                ", lot='" + lot + '\'' +
                ", address='" + address + '\'' +
                ", permit='" + permit + '\'' +
                ", status='" + status + '\'' +
                ", foodItems='" + foodItems + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", schedule='" + schedule + '\'' +
                ", approved='" + approved + '\'' +
                ", location='" + location + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
