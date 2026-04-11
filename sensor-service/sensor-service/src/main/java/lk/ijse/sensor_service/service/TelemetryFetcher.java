package lk.ijse.sensor_service.service;

import lk.ijse.sensor_service.client.AutomationClient;
import lk.ijse.sensor_service.dto.TelemetryDTO;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TelemetryFetcher {

    private final AutomationClient automationClient;
    private final RestTemplate restTemplate = new RestTemplate();

    public TelemetryFetcher(AutomationClient automationClient) {
        this.automationClient = automationClient;
    }

//    @Scheduled(fixedRate = 10000) // තත්පර 10න් 10ට
//    public void fetchAndPush() {
//        String externalApiUrl = "http://104.211.95.241:8080/api/devices/telemetry/DEV-9c242d11";
//
//        try {
//            // 1. External API එකෙන් ඩේටා ගන්නවා
//            TelemetryDTO data = restTemplate.getForObject(externalApiUrl, TelemetryDTO.class);
//
//            if (data != null) {
//                System.out.println("Fetched Temp: " + data.getValue().getTemperature());
//
//                // 2. ඒ ඩේටා ටික Automation Service එකට යවනවා
//                automationClient.sendTelemetry(data);
//                System.out.println("Data pushed to Automation Service!");
//            }
//        } catch (Exception e) {
//            System.err.println("Error fetching data: " + e.getMessage());
//        }
//    }
    // sensor-service -> TelemetryFetcher.java
    @Scheduled(fixedRate = 10000)
    public void fetchAndPush() {
        String deviceId = "DEV-9c242d11"; // ඔයාට ලැබුණු ID එක
        String externalApiUrl = "http://104.211.95.241:8080/api/devices/telemetry/" + deviceId;

        try {
            // බාහිර API එකට කතා කරනවා
            TelemetryDTO data = restTemplate.getForObject(externalApiUrl, TelemetryDTO.class);

            if (data == null) {
                throw new RuntimeException("API Response is null");
            }
            automationClient.sendTelemetry(data);

        } catch (Exception e) {
            System.err.println("External API down. Sending Mock Data for testing...");

            // API එක වැඩ නැත්නම් අපිම Mock Data එකක් හදමු
            TelemetryDTO mockData = new TelemetryDTO();
            mockData.setDeviceId(deviceId);
            mockData.setZoneId("ZONE-B1"); // ඔයා කලින් හදපු Zone Code එක

            TelemetryDTO.Value val = new TelemetryDTO.Value();
            val.setTemperature(35.0); // අපි හිතමු රස්නය වැඩියි කියලා (Limit එක 30 නම්)
            val.setHumidity(65.0);
            mockData.setValue(val);

            automationClient.sendTelemetry(mockData);
        }
    }

//    ********************** real IOT API ************************
//    @Scheduled(fixedRate = 10000)
//    public void fetchAndPush() {
//        // 1. Postman එකෙන් ලැබුණු ඇත්තම Device ID එක මෙතනට දාන්න
//        String deviceId = "DEV-a88497f3";
//        String externalApiUrl = "http://104.211.95.241:8080/api/devices/telemetry/" + deviceId;
//
//        try {
//            System.out.println("Fetching real-time data from IoT API for: " + deviceId);
//
//            // 2. බාහිර API එකට GET request එකක් යවනවා
//            TelemetryDTO data = restTemplate.getForObject(externalApiUrl, TelemetryDTO.class);
//
//            if (data != null) {
//                // 3. සාර්ථකව ඩේටා ලැබුණොත් Automation Service එකට යවනවා
//                System.out.println("Fetched Temp: " + data.getValue().getTemperature());
//                automationClient.sendTelemetry(data);
//                System.out.println("Successfully pushed to Automation Service!");
//            }
//
//        } catch (Exception e) {
//            // මොනවා හරි හේතුවකට API එක වැඩ නැත්නම් මෙතනින් Error එක බලාගන්න පුළුවන්
//            System.err.println("IoT API Error: " + e.getMessage());
//        }
//    }

}
