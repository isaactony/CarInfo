package com.carmarket.analyzer.controller;

import com.carmarket.analyzer.dto.CarResponse;
import com.carmarket.analyzer.dto.CarSearchRequest;
import com.carmarket.analyzer.model.Car;
import com.carmarket.analyzer.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class CarController {
    
    @Autowired
    private CarService carService;
    
    // Search cars by criteria
    @PostMapping("/search")
    public ResponseEntity<List<CarResponse>> searchCars(@RequestBody CarSearchRequest request) {
        try {
            List<CarResponse> cars = carService.searchCars(request);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Get all cars
    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        try {
            List<CarResponse> cars = carService.getAllCars();
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get car by ID
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable Long id) {
        try {
            CarResponse car = carService.getCarById(id);
            return ResponseEntity.ok(car);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get cars by make
    @GetMapping("/make/{make}")
    public ResponseEntity<List<CarResponse>> getCarsByMake(@PathVariable String make) {
        try {
            List<CarResponse> cars = carService.getCarsByMake(make);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get cars by make and model
    @GetMapping("/make/{make}/model/{model}")
    public ResponseEntity<List<CarResponse>> getCarsByMakeAndModel(
            @PathVariable String make, 
            @PathVariable String model) {
        try {
            List<CarResponse> cars = carService.getCarsByMakeAndModel(make, model);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get cars by location
    @GetMapping("/location/{location}")
    public ResponseEntity<List<CarResponse>> getCarsByLocation(@PathVariable String location) {
        try {
            List<CarResponse> cars = carService.getCarsByLocation(location);
            return ResponseEntity.ok(cars);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Create new car
    @PostMapping
    public ResponseEntity<CarResponse> createCar(@RequestBody Car car) {
        try {
            CarResponse createdCar = carService.saveCar(car);
            return ResponseEntity.ok(createdCar);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Update car price
    @PutMapping("/{id}/price")
    public ResponseEntity<CarResponse> updateCarPrice(
            @PathVariable Long id, 
            @RequestParam double newPrice) {
        try {
            CarResponse updatedCar = carService.updateCarPrice(id, newPrice);
            return ResponseEntity.ok(updatedCar);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    // Get price analysis for make/model
    @GetMapping("/analysis/make/{make}/model/{model}")
    public ResponseEntity<Double> getAveragePrice(
            @PathVariable String make, 
            @PathVariable String model) {
        try {
            double avgPrice = carService.getAveragePriceByMakeAndModel(make, model);
            return ResponseEntity.ok(avgPrice);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get price analysis for make/model/year
    @GetMapping("/analysis/make/{make}/model/{model}/year/{year}")
    public ResponseEntity<Double> getAveragePriceByYear(
            @PathVariable String make, 
            @PathVariable String model, 
            @PathVariable int year) {
        try {
            double avgPrice = carService.getAveragePriceByMakeModelAndYear(make, model, year);
            return ResponseEntity.ok(avgPrice);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get count by make/model
    @GetMapping("/count/make/{make}/model/{model}")
    public ResponseEntity<Long> getCountByMakeAndModel(
            @PathVariable String make, 
            @PathVariable String model) {
        try {
            long count = carService.getCountByMakeAndModel(make, model);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
