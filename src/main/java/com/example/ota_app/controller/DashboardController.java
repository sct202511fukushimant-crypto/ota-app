package com.example.ota_app.controller;

import com.example.ota_app.service.DashboardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {
    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService){
        this.dashboardService = dashboardService;
    }
    // ダッシュボード
    @GetMapping("/dashboard")
    public String showDashboard(Model model, HttpSession session) {
        if (session.getAttribute("loggedIn") == null){
            return "redirect:/login";
        }
        model.addAttribute("todayReservations", dashboardService.getTodayReservations());
        model.addAttribute("weeklyCount", dashboardService.getWeeklyCount());
        model.addAttribute("monthlyCount", dashboardService.getMonthlyCount());
        model.addAttribute("countByOta", dashboardService.getCountByOta());
        model.addAttribute("countByStatus", dashboardService.getCountByStatus());
        return "dashboard";
    }
}
