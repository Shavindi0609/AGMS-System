package lk.ijse.sensor_service.client;

import lk.ijse.sensor_service.dto.TelemetryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "AUTOMATION-SERVICE") // Eureka වල register වෙලා තියෙන නම
public interface AutomationClient {
    @PostMapping("/api/automation/process")
    void sendTelemetry(TelemetryDTO telemetry);
}
