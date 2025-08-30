package com.carmarket.analyzer.dto;

import java.util.List;

public class CarSearchRequest {
    private String make;
    private String model;
    private Integer yearFrom;
    private Integer yearTo;
    private String location;
    private Double priceFrom;
    private Double priceTo;
    private String fuelType;
    private String transmission;
    private Double mileageFrom;
    private Double mileageTo;
    private List<String> features;

    // Default constructor
    public CarSearchRequest() {}

    // Getters and Setters
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYearFrom() {
        return yearFrom;
    }

    public void setYearFrom(Integer yearFrom) {
        this.yearFrom = yearFrom;
    }

    public Integer getYearTo() {
        return yearTo;
    }

    public void setYearTo(Integer yearTo) {
        this.yearTo = yearTo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Double priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Double getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(Double priceTo) {
        this.priceTo = priceTo;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public Double getMileageFrom() {
        return mileageFrom;
    }

    public void setMileageFrom(Double mileageFrom) {
        this.mileageFrom = mileageFrom;
    }

    public Double getMileageTo() {
        return mileageTo;
    }

    public void setMileageTo(Double mileageTo) {
        this.mileageTo = mileageTo;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }

    @Override
    public String toString() {
        return "CarSearchRequest{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", yearFrom=" + yearFrom +
                ", yearTo=" + yearTo +
                ", location='" + location + '\'' +
                ", priceFrom=" + priceFrom +
                ", priceTo=" + priceTo +
                ", fuelType='" + fuelType + '\'' +
                ", transmission='" + transmission + '\'' +
                ", mileageFrom=" + mileageFrom +
                ", mileageTo=" + mileageTo +
                ", features=" + features +
                '}';
    }
}
