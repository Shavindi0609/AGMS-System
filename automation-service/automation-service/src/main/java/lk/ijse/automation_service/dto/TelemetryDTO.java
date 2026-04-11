package lk.ijse.automation_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelemetryDTO {

    private String deviceId;
    private String zoneId;
    private Value value;

    @Data
    public static class Value {
        private double temperature;
        private double humidity;
    }
}
