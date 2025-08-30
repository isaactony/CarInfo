package com.carmarket.analyzer.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorite_searches")
public class FavoriteSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(name = "search_criteria", columnDefinition = "TEXT")
    private String searchCriteria; // JSON string of search parameters
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Default constructor
    public FavoriteSearch() {
        this.createdAt = LocalDateTime.now();
    }

    // Constructor with fields
    public FavoriteSearch(User user, String searchCriteria) {
        this();
        this.user = user;
        this.searchCriteria = searchCriteria;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "FavoriteSearch{" +
                "id=" + id +
                ", user=" + user +
                ", searchCriteria='" + searchCriteria + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
