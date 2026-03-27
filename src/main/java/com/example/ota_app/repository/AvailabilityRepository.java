package com.example.ota_app.repository;

import com.example.ota_app.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.Optional;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    Optional<Availability> findByDateAndTimeSlotId(LocalDate date, Long timeSlotId);

    boolean existsByDateAndTimeSlotIdAndAvailableTrue(LocalDate date, Long timeSlotId);
}
