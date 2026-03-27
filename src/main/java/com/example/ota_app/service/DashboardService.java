package com.example.ota_app.service;

import com.example.ota_app.entity.Reservation;
import com.example.ota_app.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final ReservationRepository reservationRepository;

    public DashboardService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 今日の予約を取得
    public List<Reservation> getTodayReservations() {
        return reservationRepository.findByBookingDate(LocalDate.now());
    }

    // 今週の予約件数
    public long getWeeklyCount() {
        LocalDate start = LocalDate.now().with(java.time.DayOfWeek.MONDAY);
        LocalDate end = start.plusDays(6);
        return reservationRepository.countByBookingDateBetween(start, end);
    }

    // 今月の予約件数
    public long getMonthlyCount() {
        LocalDate start = LocalDate.now().withDayOfMonth(1);
        LocalDate end = start.plusMonths(1).minusDays(1);
        return reservationRepository.countByBookingDateBetween(start, end);
    }

    // OTA別の予約件数
    public Map<String, Long> getCountByOta() {
        return reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Reservation::getOtaSource,
                        Collectors.counting()
                ));
    }

    // ステータス別の予約件数
    public Map<String, Long> getCountByStatus() {
        return reservationRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Reservation::getStatus,
                        Collectors.counting()
                ));
    }
}
