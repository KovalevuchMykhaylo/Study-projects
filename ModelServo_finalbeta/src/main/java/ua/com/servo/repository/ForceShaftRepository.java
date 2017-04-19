package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.ForceShaft;
import ua.com.servo.entity.Size;

public interface ForceShaftRepository extends JpaRepository<ForceShaft, Integer>, JpaSpecificationExecutor<ForceShaft> {
ForceShaft findByforceShaft(Double forceShaft);

@Query("SELECT a FROM ForceShaft a LEFT JOIN FETCH a.modelServo")
List<ForceShaft> findAll();

}
