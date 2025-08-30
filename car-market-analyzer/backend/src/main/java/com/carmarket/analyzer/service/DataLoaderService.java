package com.carmarket.analyzer.service;

import com.carmarket.analyzer.model.Car;
import com.carmarket.analyzer.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DataLoaderService implements CommandLineRunner {
    
    @Autowired
    private CarRepository carRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Only load data if database is empty
        if (carRepository.count() == 0) {
            loadSampleData();
        }
    }
    
    private void loadSampleData() {
        List<Car> sampleCars = Arrays.asList(
            // Toyota Camry
            new Car("Toyota", "Camry", 2020, "New York", 25000.0),
            new Car("Toyota", "Camry", 2021, "Los Angeles", 27000.0),
            new Car("Toyota", "Camry", 2019, "Chicago", 23000.0),
            new Car("Toyota", "Camry", 2022, "Houston", 29000.0),
            
            // Honda Accord
            new Car("Honda", "Accord", 2020, "Miami", 26000.0),
            new Car("Honda", "Accord", 2021, "Seattle", 28000.0),
            new Car("Honda", "Accord", 2019, "Denver", 24000.0),
            new Car("Honda", "Accord", 2022, "Phoenix", 30000.0),
            
            // Ford F-150
            new Car("Ford", "F-150", 2020, "Dallas", 45000.0),
            new Car("Ford", "F-150", 2021, "Atlanta", 47000.0),
            new Car("Ford", "F-150", 2019, "Las Vegas", 43000.0),
            new Car("Ford", "F-150", 2022, "San Antonio", 49000.0),
            
            // Chevrolet Silverado
            new Car("Chevrolet", "Silverado", 2020, "San Diego", 44000.0),
            new Car("Chevrolet", "Silverado", 2021, "Austin", 46000.0),
            new Car("Chevrolet", "Silverado", 2019, "Nashville", 42000.0),
            new Car("Chevrolet", "Silverado", 2022, "Charlotte", 48000.0),
            
            // BMW 3 Series
            new Car("BMW", "3 Series", 2020, "Boston", 42000.0),
            new Car("BMW", "3 Series", 2021, "Portland", 44000.0),
            new Car("BMW", "3 Series", 2019, "Minneapolis", 40000.0),
            new Car("BMW", "3 Series", 2022, "Salt Lake City", 46000.0),
            
            // Mercedes-Benz C-Class
            new Car("Mercedes-Benz", "C-Class", 2020, "Philadelphia", 45000.0),
            new Car("Mercedes-Benz", "C-Class", 2021, "San Francisco", 47000.0),
            new Car("Mercedes-Benz", "C-Class", 2019, "Detroit", 43000.0),
            new Car("Mercedes-Benz", "C-Class", 2022, "Orlando", 49000.0),
            
            // Tesla Model 3
            new Car("Tesla", "Model 3", 2020, "San Jose", 38000.0),
            new Car("Tesla", "Model 3", 2021, "Raleigh", 40000.0),
            new Car("Tesla", "Model 3", 2019, "Columbus", 36000.0),
            new Car("Tesla", "Model 3", 2022, "Fort Worth", 42000.0),
            
            // Nissan Altima
            new Car("Nissan", "Altima", 2020, "Indianapolis", 24000.0),
            new Car("Nissan", "Altima", 2021, "San Francisco", 26000.0),
            new Car("Nissan", "Altima", 2019, "Jacksonville", 22000.0),
            new Car("Nissan", "Altima", 2022, "Columbus", 28000.0),
            
            // Hyundai Sonata
            new Car("Hyundai", "Sonata", 2020, "Fort Worth", 23000.0),
            new Car("Hyundai", "Sonata", 2021, "Charlotte", 25000.0),
            new Car("Hyundai", "Sonata", 2019, "Detroit", 21000.0),
            new Car("Hyundai", "Sonata", 2022, "El Paso", 27000.0)
        );
        
        // Set additional properties for each car
        for (Car car : sampleCars) {
            setCarProperties(car);
        }
        
        // Save all cars
        carRepository.saveAll(sampleCars);
        System.out.println("Loaded " + sampleCars.size() + " sample cars into database");
    }
    
    private void setCarProperties(Car car) {
        // Set engine specs based on make/model
        if (car.getMake().equals("Toyota") && car.getModel().equals("Camry")) {
            car.setEngineSpecs("2.5L 4-Cylinder");
            car.setFuelType("Gasoline");
            car.setTransmission("Automatic");
            car.setMileage(15000 + (2023 - car.getYear()) * 12000);
            car.setSafetyRating(5);
        } else if (car.getMake().equals("Honda") && car.getModel().equals("Accord")) {
            car.setEngineSpecs("1.5L Turbo 4-Cylinder");
            car.setFuelType("Gasoline");
            car.setTransmission("CVT");
            car.setMileage(14000 + (2023 - car.getYear()) * 11500);
            car.setSafetyRating(5);
        } else if (car.getMake().equals("Ford") && car.getModel().equals("F-150")) {
            car.setEngineSpecs("3.5L EcoBoost V6");
            car.setFuelType("Gasoline");
            car.setTransmission("10-Speed Automatic");
            car.setMileage(18000 + (2023 - car.getYear()) * 15000);
            car.setSafetyRating(5);
        } else if (car.getMake().equals("Chevrolet") && car.getModel().equals("Silverado")) {
            car.setEngineSpecs("5.3L V8");
            car.setFuelType("Gasoline");
            car.setTransmission("8-Speed Automatic");
            car.setMileage(17000 + (2023 - car.getYear()) * 14000);
            car.setSafetyRating(5);
        } else if (car.getMake().equals("BMW") && car.getModel().equals("3 Series")) {
            car.setEngineSpecs("2.0L Turbo 4-Cylinder");
            car.setFuelType("Gasoline");
            car.setTransmission("8-Speed Automatic");
            car.setMileage(12000 + (2023 - car.getYear()) * 10000);
            car.setSafetyRating(5);
        } else if (car.getMake().equals("Mercedes-Benz") && car.getModel().equals("C-Class")) {
            car.setEngineSpecs("2.0L Turbo 4-Cylinder");
            car.setFuelType("Gasoline");
            car.setTransmission("9-Speed Automatic");
            car.setMileage(11000 + (2023 - car.getYear()) * 9500);
            car.setSafetyRating(5);
        } else if (car.getMake().equals("Tesla") && car.getModel().equals("Model 3")) {
            car.setEngineSpecs("Dual Motor AWD");
            car.setFuelType("Electric");
            car.setTransmission("Single-Speed");
            car.setMileage(8000 + (2023 - car.getYear()) * 8000);
            car.setSafetyRating(5);
        } else if (car.getMake().equals("Nissan") && car.getModel().equals("Altima")) {
            car.setEngineSpecs("2.5L 4-Cylinder");
            car.setFuelType("Gasoline");
            car.setTransmission("CVT");
            car.setMileage(16000 + (2023 - car.getYear()) * 12500);
            car.setSafetyRating(4);
        } else if (car.getMake().equals("Hyundai") && car.getModel().equals("Sonata")) {
            car.setEngineSpecs("2.5L 4-Cylinder");
            car.setFuelType("Gasoline");
            car.setTransmission("8-Speed Automatic");
            car.setMileage(15500 + (2023 - car.getYear()) * 12000);
            car.setSafetyRating(4);
        }
    }
}
