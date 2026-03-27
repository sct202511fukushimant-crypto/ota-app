package com.example.ota_app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String otaSource;

    private String otaBookingRef;

    @ManyToOne
    @JoinColumn(name = "time_slot_id")
    private TimeSlot timeSlot;

    @Column(nullable = false)
    private LocalDate bookingDate;

    @Column(nullable = false)
    private  String customerName;

    @Column(nullable = false)
    private String customerEmail;

    private String customerPhone;

    @Column(nullable = false)
    private int numGuests;

    @Column(nullable = false)
    private String status;

    private String notes;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Gettrs and Setters
    public Long getId() { return id;} 
    public void setId(Long id) { this.id = id;}  

    public String getOtaSource() { return otaSource;}
    public void setOtaSource( String otaSource) { this.otaSource = otaSource; }

    public String getOtaBookingRef() { return otaBookingRef; }
    public void setOtaBookingRef(String otaBookingRef) { this.otaBookingRef = otaBookingRef;}

    public TimeSlot getTimeSlot() { return timeSlot; }
    public void setTimeSlot(TimeSlot timeSlot ) { this.timeSlot = timeSlot;}

    public LocalDate getBookingDate() { return bookingDate;}
    public void setBookingDate(LocalDate bookingDate) { this.bookingDate = bookingDate;}

    public String getCustomerName() { return customerName;}
    public void setCustomerName(String customerName) { this.customerName = customerName;}

    public String getCustomerEmail() { return customerEmail;}
    public void setCustomerEmail(String customerEmail) { this.customerEmail = customerEmail;}

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone;}

    public int getNumGuests() { return numGuests;}
    public void setNumGuests(int numGuests) { this.numGuests = numGuests;}

    public String getStatus() { return status;}
    public void setStatus(String status)  { this.status = status;}

    public String getNotes() { return notes;}
    public void setNotes(String notes) { this.notes = notes;}

    public LocalDateTime getCreatedAt() { return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;}

    public LocalDateTime getUpdatedAt() { return updatedAt;}
    public void setUpdatedAt(LocalDateTime updateAt) { this.updatedAt = updateAt;}
}
