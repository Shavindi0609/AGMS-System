package lk.ijse.zone_service.controller;

import lk.ijse.zone_service.entity.Zone;
import lk.ijse.zone_service.repository.ZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/zones") // API Gateway එකේ අපි දුන්න path එකට ගැලපෙන්න
public class ZoneController {

    @Autowired
    private ZoneRepository zoneRepository;

    @PostMapping
    public ResponseEntity<Zone> saveZone(@RequestBody Zone zone) {
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
