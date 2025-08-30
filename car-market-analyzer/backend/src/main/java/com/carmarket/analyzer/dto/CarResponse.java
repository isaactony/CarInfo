package com.carmarket.analyzer.dto;

import java.time.LocalDateTime;

public class CarResponse {
    private Long id;
    private String make;
    private String model;
    private int year;
    private String location;
    private double price;
    private String engineSpecs;
    private String fuelType;
    private String transmission;
    private double mileage;
    private double safetyRating;
    private LocalDateTime createdAt;

    // Default constructor
    public CarResponse() {}

    // Constructor from Car entity
    public CarResponse(Long id, String make, String model, int year, String location, 
                      double price, String engineSpecs, String fuelType, String transmission, 
                      double mileage, double safetyRating, LocalDateTime createdAt) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.location = location;
        this.price = price;
        this.engineSpecs = engineSpecs;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.mileage = mileage;
        this.safetyRating = safetyRating;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEngineSpecs() {
        return engineSpecs;
    }

    public void setEngineSpecs(String engineSpecs) {
        this.engineSpecs = engineSpecs;
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

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getSafetyRating() {
        return safetyRating;
    }

    public void setSafetyRating(double safetyRating) {
        this.safetyRating = safetyRating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "CarResponse{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", engineSpecs='" + engineSpecs + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", transmission='" + transmission + '\'' +
                ", mileage=" + mileage +
                ", safetyRating=" + safetyRating +
                ", createdAt=" + createdAt +
                '}';
    }
}
