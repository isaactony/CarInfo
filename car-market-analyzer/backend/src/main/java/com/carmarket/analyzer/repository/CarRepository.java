package com.carmarket.analyzer.repository;

import com.carmarket.analyzer.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    
    // Basic search methods
    List<Car> findByMake(String make);
    List<Car> findByMakeAndModel(String make, String model);
    List<Car> findByMakeAndModelAndYear(String make, String model, int year);
    List<Car> findByLocation(String location);
    
    // Price range queries
    List<Car> findByPriceBetween(double minPrice, double maxPrice);
    List<Car> findByMakeAndPriceBetween(String make, double minPrice, double maxPrice);
    
    // Year range queries
    List<Car> findByYearBetween(int yearFrom, int yearTo);
    List<Car> findByMakeAndYearBetween(String make, int yearFrom, int yearTo);
    
    // Fuel type and transmission
    List<Car> findByFuelType(String fuelType);
    List<Car> findByTransmission(String transmission);
    
    // Mileage queries
    List<Car> findByMileageLessThan(double maxMileage);
    List<Car> findByMileageBetween(double minMileage, double maxMileage);
    
    // Complex search query
    @Query("SELECT c FROM Car c WHERE " +
           "(:make IS NULL OR c.make = :make) AND " +
           "(:model IS NULL OR c.model = :model) AND " +
           "(:yearFrom IS NULL OR c.year >= :yearFrom) AND " +
           "(:yearTo IS NULL OR c.year <= :yearTo) AND " +
           "(:location IS NULL OR c.location = :location) AND " +
           "(:priceFrom IS NULL OR c.price >= :priceFrom) AND " +
           "(:priceTo IS NULL OR c.price <= :priceTo) AND " +
           "(:fuelType IS NULL OR c.fuelType = :fuelType) AND " +
           "(:transmission IS NULL OR c.transmission = :transmission) AND " +
           "(:mileageFrom IS NULL OR c.mileage >= :mileageFrom) AND " +
           "(:mileageTo IS NULL OR c.mileage <= :mileageTo)")
    List<Car> findCarsByCriteria(
            @Param("make") String make,
            @Param("model") String model,
            @Param("yearFrom") Integer yearFrom,
            @Param("yearTo") Integer yearTo,
            @Param("location") String location,
            @Param("priceFrom") Double priceFrom,
            @Param("priceTo") Double priceTo,
            @Param("fuelType") String fuelType,
            @Param("transmission") String transmission,
            @Param("mileageFrom") Double mileageFrom,
            @Param("mileageTo") Double mileageTo
    );
    
    // Statistics queries
    @Query("SELECT AVG(c.price) FROM Car c WHERE c.make = :make AND c.model = :model")
    Double getAveragePriceByMakeAndModel(@Param("make") String make, @Param("model") String model);
    
    @Query("SELECT AVG(c.price) FROM Car c WHERE c.make = :make AND c.model = :model AND c.year = :year")
    Double getAveragePriceByMakeModelAndYear(@Param("make") String make, @Param("model") String model, @Param("year") int year);
    
    @Query("SELECT COUNT(c) FROM Car c WHERE c.make = :make AND c.model = :model")
    Long getCountByMakeAndModel(@Param("make") String make, @Param("model") String model);
}
