package lk.ijse.crop_service.repository;

import lk.ijse.crop_service.entity.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    // අවශ්‍ය නම් Zone එක අනුව Crops සෙවීමට මෙතඩ් එකක්
    List<Crop> findByZoneCode(String zoneCode);
}
