package com.carmarket.analyzer.controller;

import com.carmarket.analyzer.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {
    
    @Autowired
    private AnalyticsService analyticsService;
    
    // Get market overview
    @GetMapping("/overview")
    public ResponseEntity<Map<String, Object>> getMarketOverview() {
        try {
            Map<String, Object> overview = analyticsService.getMarketOverview();
            return ResponseEntity.ok(overview);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get price analysis for specific make/model
    @GetMapping("/price-analysis/make/{make}/model/{model}")
    public ResponseEntity<Map<String, Object>> getPriceAnalysis(
            @PathVariable String make, 
            @PathVariable String model) {
        try {
            Map<String, Object> analysis = analyticsService.getPriceAnalysis(make, model);
            return ResponseEntity.ok(analysis);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get price trends for specific car
    @GetMapping("/price-trends/{carId}")
    public ResponseEntity<Map<String, Object>> getPriceTrends(
            @PathVariable Long carId, 
            @RequestParam(defaultValue = "30") int days) {
        try {
            Map<String, Object> trends = analyticsService.getPriceTrends(carId, days);
            return ResponseEntity.ok(trends);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get market insights
    @GetMapping("/insights")
    public ResponseEntity<Map<String, Object>> getMarketInsights() {
        try {
            Map<String, Object> insights = analyticsService.getMarketInsights();
            return ResponseEntity.ok(insights);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get popular makes
    @GetMapping("/popular-makes")
    public ResponseEntity<Map<String, Object>> getPopularMakes() {
        try {
            Map<String, Object> overview = analyticsService.getMarketOverview();
            return ResponseEntity.ok(Map.of("topMakes", overview.get("topMakes")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get popular locations
    @GetMapping("/popular-locations")
    public ResponseEntity<Map<String, Object>> getPopularLocations() {
        try {
            Map<String, Object> overview = analyticsService.getMarketOverview();
            return ResponseEntity.ok(Map.of("topLocations", overview.get("topLocations")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
    
    // Get price range statistics
    @GetMapping("/price-range")
    public ResponseEntity<Map<String, Object>> getPriceRange() {
        try {
            Map<String, Object> overview = analyticsService.getMarketOverview();
            return ResponseEntity.ok(Map.of("priceRange", overview.get("priceRange")));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
