package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.SpeedRotation;
import ua.com.servo.entity.Type;

public interface TypeRepository extends JpaRepository<Type, Integer>, JpaSpecificationExecutor<Type>{
Type findByType(String type);

@Query("SELECT a FROM Type a LEFT JOIN FETCH a.modelServo")
List<Type> findAll();
}
