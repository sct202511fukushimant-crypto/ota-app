package com.example.ota_app.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.ota_app.entity.Reservation;
import com.example.ota_app.repository.ReservationRepository;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    // 予約を保存する
    public Reservation save(Reservation reservation) {
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());
        return reservationRepository.save(reservation);
    }
    // 全件取得
    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    // IDで取得
    public Optional<Reservation> findById(Long id){
        return reservationRepository.findById(id);
    }

    // 日付で検索
    public List<Reservation> findByBookingDate(LocalDate date){
        return reservationRepository.findByBookingDate(date);
    }

    // OTA別に検索
    public List<Reservation> findByOtaSource(String otaSource) {
        return reservationRepository.findByOtaSource(otaSource);
    }
    
    // ステータス別に検索
    public List<Reservation> findByStatus(String status) {
        return reservationRepository.findByStatus(status);
    }

    // ステータスを更新する
    public void updateStatus(Long id, String status) {
        reservationRepository.findById(id).ifPresent(r -> {
            r.setStatus(status);
            r.setUpdatedAt(LocalDateTime.now());
            reservationRepository.save(r);
        });
    }
}
