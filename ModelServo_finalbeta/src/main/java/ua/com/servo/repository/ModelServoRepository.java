package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.ModelServo;

public interface ModelServoRepository extends JpaRepository<ModelServo, Integer>, JpaSpecificationExecutor<ModelServo> {
	ModelServo findByName(String name);

	@Query(value = "SELECT f FROM ModelServo f LEFT JOIN FETCH f.forceShaft LEFT JOIN FETCH f.gearType LEFT JOIN FETCH f.powerVoltage LEFT JOIN FETCH f.rotationAngle LEFT JOIN FETCH f.size LEFT JOIN FETCH f.speedRotation LEFT JOIN FETCH f.type LEFT JOIN FETCH f.weight", countQuery = "SELECT count(f.id) FROM ModelServo f")
	Page<ModelServo> findAll(Pageable pageable);

	@Override
	@Query("SELECT a FROM ModelServo a LEFT JOIN FETCH a.forceShaft LEFT JOIN FETCH a.size")
	List<ModelServo> findAll();

	@Query("SELECT f FROM ModelServo f LEFT JOIN FETCH f.forceShaft LEFT JOIN FETCH f.gearType LEFT JOIN FETCH f.powerVoltage LEFT JOIN FETCH f.rotationAngle LEFT JOIN FETCH f.size LEFT JOIN FETCH f.speedRotation LEFT JOIN FETCH f.type LEFT JOIN FETCH f.weight WHERE f.id=?1")
	ModelServo findOne(Integer id);

	@Query("SELECT sc.count FROM User u JOIN u.shopingCart sc WHERE u.id=?1")
	Integer findCount(int id);

	@Query("SELECT i FROM ModelServo i JOIN i.shopingCarts sc JOIN sc.users u WHERE u.id=?1")
	List<ModelServo> findByUserId(int userId);

}
