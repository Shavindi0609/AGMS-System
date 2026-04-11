package lk.ijse.zone_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "zones")
public class Zone {
    @Id
    private String zoneCode; // Assignment එකේ විදිහට String code එකක් පාවිච්චි කරමු
    private String zoneName;
    private String zoneLocation;
    private Double zoneSize;
    private String zoneDescription;
    private String deviceId;
    private Double minTemp; // අලුතින් එකතු කරන්න
    private Double maxTemp; // අලුතින් එකතු කරන්න
}
