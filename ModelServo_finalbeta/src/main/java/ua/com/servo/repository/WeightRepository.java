package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.Type;
import ua.com.servo.entity.Weight;

public interface WeightRepository extends JpaRepository<Weight, Integer>, JpaSpecificationExecutor<Weight>{
Weight findByweight (int weight);

@Query("SELECT a FROM Weight a LEFT JOIN FETCH a.modelServo")
List<Weight> findAll();
}