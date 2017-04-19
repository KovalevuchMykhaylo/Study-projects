package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.ForceShaft;
import ua.com.servo.entity.GearType;


public interface GearTypeRepository extends JpaRepository<GearType, Integer>, JpaSpecificationExecutor<GearType> {
GearType findByGearType(String gearType);

@Query("SELECT a FROM GearType a LEFT JOIN FETCH a.modelServo")
List<GearType> findAll();
}
