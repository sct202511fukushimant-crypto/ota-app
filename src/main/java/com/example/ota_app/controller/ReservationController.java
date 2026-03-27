package com.example.ota_app.controller;

import com.example.ota_app.entity.Reservation;
import com.example.ota_app.service.ReservationService;
import com.example.ota_app.service.OtaSyncService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final OtaSyncService otaSyncService;

    public ReservationController(ReservationService reservationService, OtaSyncService otaSyncService) {
        this.reservationService = reservationService;
        this.otaSyncService = otaSyncService;
    }

    // OTAからの予約受信API
    @PostMapping("/api/reservations")
    @ResponseBody
    public Map<String, Object> receiveBooking(@RequestBody Map<String, Object> dto) {
        Reservation reservation = new Reservation();
        reservation.setOtaSource((String) dto.get("otaSource"));
        reservation.setCustomerName((String) dto.get("customerName"));
        reservation.setCustomerEmail((String) dto.get("customerEmail"));
        reservation.setCustomerPhone((String) dto.get("customerPhone"));
        reservation.setNumGuests((Integer) dto.get("numGuests"));
        reservation.setBookingDate(LocalDate.parse((String) dto.get("bookingDate")));
        reservation.setStatus("CONFIRMED");
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        Reservation saved = reservationService.save(reservation);

        // 売り止め送信
        otaSyncService.sendSoldOut(saved.getBookingDate(), 1L);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "予約を受け付けました");
        response.put("reservationId", saved.getId());
        return response;
    }

    // 予約一覧画面
    @GetMapping("/reservations")
    public String listReservations(Model model, HttpSession session) {
        if(session.getAttribute("loggedIn") == null){
            return "redirect:/login";
        }
        model.addAttribute("reservations", reservationService.findAll());
        return "reservations/list";
    }

    // 予約詳細画面
    @GetMapping("/reservations/{id}")
    public String showDetail(@PathVariable Long id, Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null){
            return "redirect:/login";
        }
        reservationService.findById(id).ifPresent(r -> model.addAttribute("reservation", r));
        return "reservations/detail";
    }

    // ステータス更新
    @PostMapping("/reservations/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam String status, HttpSession session) {
        if  (session.getAttribute("loggedIn") == null){
            return"redirect:/login";
        }
        reservationService.updateStatus(id, status);

        // キャンセルの場合は売り止め解除
        if ("CANCELLED".equals(status)) {
            otaSyncService.sendReopen(LocalDate.now(), 1L);
        }
        return "redirect:/reservations/" + id;
    }
}
