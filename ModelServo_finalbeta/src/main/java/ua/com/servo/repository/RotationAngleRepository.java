package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.PowerVoltage;
import ua.com.servo.entity.RotationAngle;

public interface RotationAngleRepository extends JpaRepository<RotationAngle, Integer>, JpaSpecificationExecutor<RotationAngle> {
	RotationAngle findByAngle (int angle);
	
	@Query("SELECT a FROM RotationAngle a LEFT JOIN FETCH a.modelServo")
	List<RotationAngle> findAll();
}
