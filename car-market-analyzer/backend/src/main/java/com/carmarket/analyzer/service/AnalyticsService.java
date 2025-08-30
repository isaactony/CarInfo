package com.carmarket.analyzer.service;

import com.carmarket.analyzer.model.Car;
import com.carmarket.analyzer.model.PriceHistory;
import com.carmarket.analyzer.repository.CarRepository;
import com.carmarket.analyzer.repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private PriceHistoryRepository priceHistoryRepository;
    
    // Get market overview statistics
    public Map<String, Object> getMarketOverview() {
        Map<String, Object> overview = new HashMap<>();
        
        // Total cars in market
        long totalCars = carRepository.count();
        overview.put("totalCars", totalCars);
        
        // Average price across all cars
        List<Car> allCars = carRepository.findAll();
        double avgPrice = allCars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0.0);
        overview.put("averagePrice", avgPrice);
        
        // Price range
        double minPrice = allCars.stream()
                .mapToDouble(Car::getPrice)
                .min()
                .orElse(0.0);
        double maxPrice = allCars.stream()
                .mapToDouble(Car::getPrice)
                .max()
                .orElse(0.0);
        overview.put("priceRange", Map.of("min", minPrice, "max", maxPrice));
        
        // Popular makes
        Map<String, Long> makeCounts = allCars.stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.counting()));
        List<Map.Entry<String, Long>> topMakes = makeCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
        overview.put("topMakes", topMakes);
        
        // Popular locations
        Map<String, Long> locationCounts = allCars.stream()
                .collect(Collectors.groupingBy(Car::getLocation, Collectors.counting()));
        List<Map.Entry<String, Long>> topLocations = locationCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toList());
        overview.put("topLocations", topLocations);
        
        return overview;
    }
    
    // Get price analysis for a specific make/model
    public Map<String, Object> getPriceAnalysis(String make, String model) {
        Map<String, Object> analysis = new HashMap<>();
        
        // Basic statistics
        double avgPrice = carRepository.getAveragePriceByMakeAndModel(make, model);
        long count = carRepository.getCountByMakeAndModel(make, model);
        
        analysis.put("make", make);
        analysis.put("model", model);
        analysis.put("averagePrice", avgPrice);
        analysis.put("totalCount", count);
        
        // Price by year
        List<Car> cars = carRepository.findByMakeAndModel(make, model);
        Map<Integer, Double> priceByYear = cars.stream()
                .collect(Collectors.groupingBy(
                        Car::getYear,
                        Collectors.averagingDouble(Car::getPrice)
                ));
        analysis.put("priceByYear", priceByYear);
        
        // Price by location
        Map<String, Double> priceByLocation = cars.stream()
                .collect(Collectors.groupingBy(
                        Car::getLocation,
                        Collectors.averagingDouble(Car::getPrice)
                ));
        analysis.put("priceByLocation", priceByLocation);
        
        // Price distribution
        List<Double> prices = cars.stream()
                .map(Car::getPrice)
                .sorted()
                .collect(Collectors.toList());
        
        if (!prices.isEmpty()) {
            analysis.put("medianPrice", getMedian(prices));
            analysis.put("priceDistribution", getPriceDistribution(prices));
        }
        
        return analysis;
    }
    
    // Get price trends for a specific car
    public Map<String, Object> getPriceTrends(Long carId, int days) {
        Map<String, Object> trends = new HashMap<>();
        
        LocalDateTime startDate = LocalDateTime.now().minus(days, ChronoUnit.DAYS);
        List<PriceHistory> priceHistory = priceHistoryRepository.getPriceTrends(carId, startDate);
        
        if (!priceHistory.isEmpty()) {
            // Price change over time
            List<Map<String, Object>> trendData = priceHistory.stream()
                    .map(ph -> {
                        Map<String, Object> data = new HashMap<>();
                        data.put("date", ph.getRecordedAt());
                        data.put("price", ph.getPrice());
                        return data;
                    })
                    .collect(Collectors.toList());
            trends.put("trendData", trendData);
            
            // Price change percentage
            double firstPrice = priceHistory.get(0).getPrice();
            double lastPrice = priceHistory.get(priceHistory.size() - 1).getPrice();
            double changePercent = ((lastPrice - firstPrice) / firstPrice) * 100;
            trends.put("priceChangePercent", changePercent);
            
            // Volatility
            double volatility = priceHistoryRepository.getPriceVolatility(carId, startDate);
            trends.put("volatility", volatility);
            
            // Average price over period
            double avgPrice = priceHistoryRepository.getAveragePriceOverPeriod(carId, startDate, LocalDateTime.now());
            trends.put("averagePrice", avgPrice);
            
            // Latest price
            List<PriceHistory> latestPriceHistory = priceHistoryRepository.getLatestPriceHistory(carId);
            if (!latestPriceHistory.isEmpty()) {
                trends.put("latestPrice", latestPriceHistory.get(0).getPrice());
            }
        }
        
        return trends;
    }
    
    // Get market insights
    public Map<String, Object> getMarketInsights() {
        Map<String, Object> insights = new HashMap<>();
        
        List<Car> allCars = carRepository.findAll();
        
        // Price trends by make
        Map<String, Double> avgPriceByMake = allCars.stream()
                .collect(Collectors.groupingBy(
                        Car::getMake,
                        Collectors.averagingDouble(Car::getPrice)
                ));
        
        // Find makes with highest and lowest average prices
        Map.Entry<String, Double> mostExpensiveMake = avgPriceByMake.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        Map.Entry<String, Double> leastExpensiveMake = avgPriceByMake.entrySet().stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);
        
        insights.put("mostExpensiveMake", mostExpensiveMake);
        insights.put("leastExpensiveMake", leastExpensiveMake);
        
        // Fuel type distribution
        Map<String, Long> fuelTypeDistribution = allCars.stream()
                .collect(Collectors.groupingBy(Car::getFuelType, Collectors.counting()));
        insights.put("fuelTypeDistribution", fuelTypeDistribution);
        
        // Transmission distribution
        Map<String, Long> transmissionDistribution = allCars.stream()
                .collect(Collectors.groupingBy(Car::getTransmission, Collectors.counting()));
        insights.put("transmissionDistribution", transmissionDistribution);
        
        // Safety rating analysis
        double avgSafetyRating = allCars.stream()
                .mapToDouble(Car::getSafetyRating)
                .average()
                .orElse(0.0);
        insights.put("averageSafetyRating", avgSafetyRating);
        
        return insights;
    }
    
    // Helper method to calculate median
    private double getMedian(List<Double> values) {
        if (values.isEmpty()) return 0.0;
        
        Collections.sort(values);
        int size = values.size();
        
        if (size % 2 == 0) {
            return (values.get(size / 2 - 1) + values.get(size / 2)) / 2.0;
        } else {
            return values.get(size / 2);
        }
    }
    
    // Helper method to get price distribution
    private Map<String, Double> getPriceDistribution(List<Double> prices) {
        if (prices.isEmpty()) return new HashMap<>();
        
        double min = prices.get(0);
        double max = prices.get(prices.size() - 1);
        double range = max - min;
        
        // Create price buckets
        Map<String, Double> distribution = new HashMap<>();
        distribution.put("under25k", prices.stream().filter(p -> p < 25000).count() * 100.0 / prices.size());
        distribution.put("25k-50k", prices.stream().filter(p -> p >= 25000 && p < 50000).count() * 100.0 / prices.size());
        distribution.put("50k-75k", prices.stream().filter(p -> p >= 50000 && p < 75000).count() * 100.0 / prices.size());
        distribution.put("75k-100k", prices.stream().filter(p -> p >= 75000 && p < 100000).count() * 100.0 / prices.size());
        distribution.put("over100k", prices.stream().filter(p -> p >= 100000).count() * 100.0 / prices.size());
        
        return distribution;
    }
}
