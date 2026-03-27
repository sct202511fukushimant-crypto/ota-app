package com.example.ota_app.repository;

import com.example.ota_app.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation>findByBookingDate(LocalDate date);
    List<Reservation>findByOtaSource(String otaSource);
    List<Reservation>findByStatus(String status);
    long countByBookingDateBetween(LocalDate from, LocalDate to);
}
