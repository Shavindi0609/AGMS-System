package lk.ijse.zone_service.controller;

import lk.ijse.zone_service.entity.Zone;
import lk.ijse.zone_service.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/zones") // API Gateway එකේ අපි දුන්න path එකට ගැලපෙන්න
public class ZoneController {

    @Autowired
    private ZoneRepository zoneRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    // ZoneController.java ඇතුළත
    @PostMapping
    public ResponseEntity<Zone> saveZone(@RequestBody Zone zone) {
        String externalApiUrl = "http://104.211.95.241:8080/api/auth/register"; // මුලින් register විය යුතුයි

        // බාහිර API එක සඳහා අවශ්‍ය තොරතුරු
        Map<String, String> authRequest = new HashMap<>();
        authRequest.put("username", "shavindi_" + zone.getZoneCode()); // Unique එකක් විය යුතුයි
        authRequest.put("password", "123456");

        try {
            // 1. බාහිර සේවාව සමඟ Register වී device එකක් සාදා ගැනීම
            // සටහන: Assignment එකට අනුව register/login වී ලැබෙන Token එක පාවිච්චි කළ යුතුය.
            // දැනට පරීක්ෂා කිරීමට සරලව Device එකක් සාදාගැනීමට උත්සාහ කරමු.

            String deviceUrl = "http://104.211.95.241:8080/api/devices";
            Map<String, String> deviceRequest = new HashMap<>();
            deviceRequest.put("name", zone.getZoneName());
            deviceRequest.put("zoneId", zone.getZoneCode());

            // මෙතැනදී ලැබෙන response එක Map එකකට ගන්න
            ResponseEntity<Map> response = restTemplate.postForEntity(deviceUrl, deviceRequest, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                String generatedDeviceId = (String) response.getBody().get("deviceId");
                zone.setDeviceId(generatedDeviceId);
            }
        } catch (Exception e) {
            // බාහිර API එකේ දෝෂයක් ආවොත් සර්වර් එක crash නොවී තිබීමට dummy ID එකක් දෙන්න
            System.err.println("External API Error: " + e.getMessage());
            zone.setDeviceId("DEV-" + java.util.UUID.randomUUID().toString().substring(0, 8));
        }

        return ResponseEntity.ok(zoneRepository.save(zone));
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    // Zone Service එකේ ZoneController.java
    @GetMapping("/{id}")
    public ResponseEntity<Zone> getZoneById(@PathVariable String id) {
        return zoneRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
