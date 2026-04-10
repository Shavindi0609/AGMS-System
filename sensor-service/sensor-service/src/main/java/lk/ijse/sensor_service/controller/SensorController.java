package lk.ijse.sensor_service.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lk.ijse.sensor_service.client.ZoneClient;
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

    @Autowired
    private ZoneClient zoneClient; // අලුතින් හදපු client එක inject කරන්න

//    @PostMapping
//    public ResponseEntity<Sensor> saveSensor(@RequestBody Sensor sensor) {
//        // මෙතනදී පස්සේ කාලෙක අපිට පුළුවන් Zone Service එකට කතා කරලා
//        // ඇත්තටම මේ zoneCode එක තියෙනවද කියලා බලන්න (OpenFeign පාවිච්චි කරලා)
//        return ResponseEntity.ok(sensorRepository.save(sensor));
//    }

    @CircuitBreaker(name = "zoneServiceBreaker", fallbackMethod = "zoneFallback")
    @PostMapping
    public ResponseEntity<?> saveSensor(@RequestBody Sensor sensor) {
        try {
            // Zone Service එකෙන් අදාළ Zone එක තියෙනවද කියලා බලනවා
            Object zone = zoneClient.getZoneById(sensor.getZoneCode());

            if (zone != null) {
                return ResponseEntity.ok(sensorRepository.save(sensor));
            }
        } catch (Exception e) {
            // Zone එක නැති වුණොත් හෝ Service එක Down නම් මෙතනට එනවා
            return ResponseEntity.badRequest().body("Invalid Zone Code: " + sensor.getZoneCode());
        }
        return ResponseEntity.internalServerError().build();
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
