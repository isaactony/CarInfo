package com.carmarket.analyzer.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "price_history")
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;
    
    @Column(nullable = false)
    private double price;
    
    @Column(name = "recorded_at")
    private LocalDateTime recordedAt;

    // Default constructor
    public PriceHistory() {
        this.recordedAt = LocalDateTime.now();
    }

    // Constructor with fields
    public PriceHistory(Car car, double price) {
        this();
        this.car = car;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    @Override
    public String toString() {
        return "PriceHistory{" +
                "id=" + id +
                ", car=" + car +
                ", price=" + price +
                ", recordedAt=" + recordedAt +
                '}';
    }
}
