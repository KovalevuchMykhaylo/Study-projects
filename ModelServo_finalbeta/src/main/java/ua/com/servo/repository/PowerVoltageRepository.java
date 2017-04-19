package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.GearType;
import ua.com.servo.entity.PowerVoltage;

public interface PowerVoltageRepository extends JpaRepository<PowerVoltage, Integer> {
PowerVoltage findByPowerVoltage (String powerVoltage);

@Query("SELECT a FROM PowerVoltage a LEFT JOIN FETCH a.modelServo")
List<PowerVoltage> findAll();
}
