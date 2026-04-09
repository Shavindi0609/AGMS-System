package lk.ijse.sensor_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sensors")
public class Sensor {

    @Id
    private String sensorCode;
    private String sensorName;
    private String sensorType; // Temperature, Humidity, etc.
    private String sensorStatus; // Active, Inactive
    private String zoneCode; // මේකෙන් තමයි Zone එක අඳුරගන්නේ
}
