package lk.ijse.sensor_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "zone-service")
public interface ZoneClient {

    // Zone Service එකේ තියෙන GetMapping එකට සමාන විය යුතුයි
    @GetMapping("/api/v1/zones/{id}")
    Object getZoneById(@PathVariable("id") String id);

}
