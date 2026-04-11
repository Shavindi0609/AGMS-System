package lk.ijse.crop_service.controller;

import lk.ijse.crop_service.entity.Crop;
import lk.ijse.crop_service.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/crops")
public class CropController {

    @Autowired
    private CropRepository cropRepository;

    // 1. භෝගයක් ඇතුළත් කිරීම (Create)
    @PostMapping
    public ResponseEntity<Crop> saveCrop(@RequestBody Crop crop) {
        return ResponseEntity.ok(cropRepository.save(crop));
    }

    // 2. සියලුම භෝග ලබාගැනීම (Read All)
    @GetMapping
    public List<Crop> getAllCrops() {
        return cropRepository.findAll();
    }

    // 3. ID එක අනුව සෙවීම (Read by ID)
    @GetMapping("/{id}")
    public ResponseEntity<Crop> getCropById(@PathVariable Long id) {
        return cropRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. යාවත්කාලීන කිරීම (Update)
    @PutMapping("/{id}")
    public ResponseEntity<Crop> updateCrop(@PathVariable Long id, @RequestBody Crop cropDetails) {
        return cropRepository.findById(id).map(crop -> {
            crop.setCropName(cropDetails.getCropName());
            crop.setStatus(cropDetails.getStatus());
            return ResponseEntity.ok(cropRepository.save(crop));
        }).orElse(ResponseEntity.notFound().build());
    }

    // 5. ඉවත් කිරීම (Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrop(@PathVariable Long id) {
        cropRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
