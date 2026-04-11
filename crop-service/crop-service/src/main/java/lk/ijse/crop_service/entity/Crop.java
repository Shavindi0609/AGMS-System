package lk.ijse.crop_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "crops")
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cropName;
    private String cropType; // උදා: Fruit, Vegetable
    private String status;   // SEEDLING, VEGETATIVE, HARVESTED
    private String zoneCode; // මේක Zone Service එකේ zoneCode එකට සමාන විය යුතුයි
}
