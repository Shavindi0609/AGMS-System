package lk.ijse.automation_service.client;

import lk.ijse.automation_service.dto.ZoneDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ZONE-SERVICE")
public interface ZoneClient {

    @GetMapping("/api/v1/zones/{id}")
    ZoneDTO getZoneById(@PathVariable("id") String id);
}
