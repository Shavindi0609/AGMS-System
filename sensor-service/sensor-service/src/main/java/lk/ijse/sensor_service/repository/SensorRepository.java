package lk.ijse.sensor_service.repository;

import lk.ijse.sensor_service.entity.Sensor;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, String>{
}
