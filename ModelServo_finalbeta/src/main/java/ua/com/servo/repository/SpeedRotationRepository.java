package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.Size;
import ua.com.servo.entity.SpeedRotation;

public interface SpeedRotationRepository extends JpaRepository<SpeedRotation, Integer>, JpaSpecificationExecutor<SpeedRotation> {
	SpeedRotation findBySpeedRotation (double speedRotation);
	
	@Query("SELECT a FROM SpeedRotation a LEFT JOIN FETCH a.modelServo")
	List<SpeedRotation> findAll();

}
