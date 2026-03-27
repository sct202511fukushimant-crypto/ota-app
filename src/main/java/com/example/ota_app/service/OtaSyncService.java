package com.example.ota_app.service;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import org.springframework.web.client.RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class OtaSyncService {
    // OTAサイトのURL（後で実際のURLに変更）
    private static final String OTA_A_URL = "http://localhost:8081/api/availability";
    private static final String OTA_B_URL = "http://localhost:8082/api/availability";

    private final RestTemplate restTemplate = new RestTemplate();

    // 売り止め送信
    public void sendSoldOut(LocalDate date, Long timeSlotId){
        sendAvailability(date, timeSlotId, false);
    }

    // 売り止め解除送信
    public void sendReopen(LocalDate date, Long timeSlotId) {
        sendAvailability(date, timeSlotId, true);
    }
    
    // 両OTAに空き状況を送信
    private void sendAvailability(LocalDate date, Long timeSlotId, boolean available) {
        Map<String, Object> body = new HashMap<>();
        body.put("date", date.toString());
        body.put("timeSlotId", timeSlotId);
        body.put("available", available);

        try {
            restTemplate.postForObject(OTA_A_URL, body, Map.class);
        } catch (Exception e) {
            System.out.println("OTA-A への送信失敗（スキップ）: " + e.getMessage());
        }
        try {
            restTemplate.postForObject(OTA_B_URL, body, Map.class);
        } catch (Exception e) {
            System.out.println("OTA-B への送信失敗（スキップ）: " + e.getMessage());
        }
    }

}
