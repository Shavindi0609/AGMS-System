package lk.ijse.automation_service.dto;

import lombok.Data;

@Data
public class ZoneDTO {

    private String zoneCode;
    private String zoneName;
    private double minTemp; // Zone එකේ තියෙන min limit එක
    private double maxTemp; // Zone එකේ තියෙන max limit එක
}
