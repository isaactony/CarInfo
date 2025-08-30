package com.carmarket.analyzer.repository;

import com.carmarket.analyzer.model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistory, Long> {
    
    List<PriceHistory> findByCarIdOrderByRecordedAtDesc(Long carId);
    
    List<PriceHistory> findByCarIdAndRecordedAtBetweenOrderByRecordedAtAsc(
            Long carId, LocalDateTime startDate, LocalDateTime endDate);
    
    // Get price trends for a specific car
    @Query("SELECT ph FROM PriceHistory ph WHERE ph.car.id = :carId " +
           "AND ph.recordedAt >= :startDate ORDER BY ph.recordedAt ASC")
    List<PriceHistory> getPriceTrends(@Param("carId") Long carId, @Param("startDate") LocalDateTime startDate);
    
    // Get average price for a car over a time period
    @Query("SELECT AVG(ph.price) FROM PriceHistory ph WHERE ph.car.id = :carId " +
           "AND ph.recordedAt BETWEEN :startDate AND :endDate")
    Double getAveragePriceOverPeriod(@Param("carId") Long carId, 
                                   @Param("startDate") LocalDateTime startDate, 
                                   @Param("endDate") LocalDateTime endDate);
    
    // Get price volatility (standard deviation) for a car
    @Query("SELECT STDDEV(ph.price) FROM PriceHistory ph WHERE ph.car.id = :carId " +
           "AND ph.recordedAt >= :startDate")
    Double getPriceVolatility(@Param("carId") Long carId, @Param("startDate") LocalDateTime startDate);
    
    // Get latest price for a car
    @Query("SELECT ph FROM PriceHistory ph WHERE ph.car.id = :carId " +
           "ORDER BY ph.recordedAt DESC")
    List<PriceHistory> getLatestPriceHistory(@Param("carId") Long carId);
}
