package lk.ijse.sensor_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

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
