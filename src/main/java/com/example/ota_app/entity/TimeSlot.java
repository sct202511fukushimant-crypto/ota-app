package com.example.ota_app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name = "time_slots")

public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String startTime;
    
    @Column(nullable = false)
    private int maxCapacity;

    private String label;

    @Column(nullable = false)
    private boolean isActive;

    // Getters and Setters
    public Long getId() { return id;}
    public void setId(Long id) { this.id = id;}

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public int getMaxCapacity() { return maxCapacity; }
    public void setMaxCapacity(int maxCapacity) { this.maxCapacity = maxCapacity;}

    public String getLabel() { return label;}
    public void setLabel(String label) { this.label = label; }
 
    public boolean isActive() { return isActive; }
    public void setActive(boolean isActive) { this.isActive = isActive; }


}
