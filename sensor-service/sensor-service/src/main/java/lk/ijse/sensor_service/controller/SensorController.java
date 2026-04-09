package lk.ijse.sensor_service.controller;

import lk.ijse.sensor_service.entity.Sensor;
import lk.ijse.sensor_service.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sensors")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @PostMapping
    public ResponseEntity<Sensor> saveSensor(@RequestBody Sensor sensor) {
        // මෙතනදී පස්සේ කාලෙක අපිට පුළුවන් Zone Service එකට කතා කරලා
        // ඇත්තටම මේ zoneCode එක තියෙනවද කියලා බලන්න (OpenFeign පාවිච්චි කරලා)
        return ResponseEntity.ok(sensorRepository.save(sensor));
    }

    @GetMapping
    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable String id) {
        return sensorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
