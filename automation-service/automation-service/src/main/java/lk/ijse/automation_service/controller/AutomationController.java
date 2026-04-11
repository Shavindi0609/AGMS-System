package lk.ijse.automation_service.controller;


import lk.ijse.automation_service.client.ZoneClient;
import lk.ijse.automation_service.dto.TelemetryDTO;
import lk.ijse.automation_service.dto.ZoneDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/automation")
public class AutomationController {

    @Autowired // මේ පේළිය අනිවාර්යයි
    private ZoneClient zoneClient;

    @PostMapping("/process")
    public void processData(@RequestBody TelemetryDTO data) {
        // 1. Zone Service එකෙන් මේ Zone එකේ limits ගන්නවා
        ZoneDTO zone = zoneClient.getZoneById(data.getZoneId());

        double currentTemp = data.getValue().getTemperature();

        // 2. Rule Engine Logic
        if (currentTemp > zone.getMaxTemp()) {
            System.out.println(">>> ALERT: TURN_FAN_ON in " + zone.getZoneName());
        } else if (currentTemp < zone.getMinTemp()) {
            System.out.println(">>> ALERT: TURN_HEATER_ON in " + zone.getZoneName());
        }
    }
}
