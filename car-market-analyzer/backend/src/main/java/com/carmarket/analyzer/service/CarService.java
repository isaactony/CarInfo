package com.carmarket.analyzer.service;

import com.carmarket.analyzer.dto.CarResponse;
import com.carmarket.analyzer.dto.CarSearchRequest;
import com.carmarket.analyzer.model.Car;
import com.carmarket.analyzer.model.PriceHistory;
import com.carmarket.analyzer.repository.CarRepository;
import com.carmarket.analyzer.repository.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    
    @Autowired
    private CarRepository carRepository;
    
    @Autowired
    private PriceHistoryRepository priceHistoryRepository;
    
    // Search cars by criteria
    public List<CarResponse> searchCars(CarSearchRequest request) {
        List<Car> cars = carRepository.findCarsByCriteria(
                request.getMake(),
                request.getModel(),
                request.getYearFrom(),
                request.getYearTo(),
                request.getLocation(),
                request.getPriceFrom(),
                request.getPriceTo(),
                request.getFuelType(),
                request.getTransmission(),
                request.getMileageFrom(),
                request.getMileageTo()
        );
        
        return cars.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Get car by ID
    public CarResponse getCarById(Long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + id));
        return convertToResponse(car);
    }
    
    // Get all cars
    public List<CarResponse> getAllCars() {
        return carRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Get cars by make
    public List<CarResponse> getCarsByMake(String make) {
        return carRepository.findByMake(make).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Get cars by make and model
    public List<CarResponse> getCarsByMakeAndModel(String make, String model) {
        return carRepository.findByMakeAndModel(make, model).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Get cars by location
    public List<CarResponse> getCarsByLocation(String location) {
        return carRepository.findByLocation(location).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }
    
    // Get price analysis for a car
    public double getAveragePriceByMakeAndModel(String make, String model) {
        Double avgPrice = carRepository.getAveragePriceByMakeAndModel(make, model);
        return avgPrice != null ? avgPrice : 0.0;
    }
    
    public double getAveragePriceByMakeModelAndYear(String make, String model, int year) {
        Double avgPrice = carRepository.getAveragePriceByMakeModelAndYear(make, model, year);
        return avgPrice != null ? avgPrice : 0.0;
    }
    
    public long getCountByMakeAndModel(String make, String model) {
        Long count = carRepository.getCountByMakeAndModel(make, model);
        return count != null ? count : 0L;
    }
    
    // Save a new car
    public CarResponse saveCar(Car car) {
        Car savedCar = carRepository.save(car);
        
        // Create initial price history entry
        PriceHistory priceHistory = new PriceHistory(savedCar, savedCar.getPrice());
        priceHistoryRepository.save(priceHistory);
        
        return convertToResponse(savedCar);
    }
    
    // Update car price and track history
    public CarResponse updateCarPrice(Long carId, double newPrice) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with id: " + carId));
        
        car.setPrice(newPrice);
        Car updatedCar = carRepository.save(car);
        
        // Record price change in history
        PriceHistory priceHistory = new PriceHistory(updatedCar, newPrice);
        priceHistoryRepository.save(priceHistory);
        
        return convertToResponse(updatedCar);
    }
    
    // Get price trends for a car
    public List<PriceHistory> getPriceTrends(Long carId, LocalDateTime startDate) {
        return priceHistoryRepository.getPriceTrends(carId, startDate);
    }
    
    // Get average price over a period
    public double getAveragePriceOverPeriod(Long carId, LocalDateTime startDate, LocalDateTime endDate) {
        Double avgPrice = priceHistoryRepository.getAveragePriceOverPeriod(carId, startDate, endDate);
        return avgPrice != null ? avgPrice : 0.0;
    }
    
    // Get price volatility
    public double getPriceVolatility(Long carId, LocalDateTime startDate) {
        Double volatility = priceHistoryRepository.getPriceVolatility(carId, startDate);
        return volatility != null ? volatility : 0.0;
    }
    
    // Convert Car entity to CarResponse DTO
    private CarResponse convertToResponse(Car car) {
        return new CarResponse(
                car.getId(),
                car.getMake(),
                car.getModel(),
                car.getYear(),
                car.getLocation(),
                car.getPrice(),
                car.getEngineSpecs(),
                car.getFuelType(),
                car.getTransmission(),
                car.getMileage(),
                car.getSafetyRating(),
                car.getCreatedAt()
        );
    }
}
