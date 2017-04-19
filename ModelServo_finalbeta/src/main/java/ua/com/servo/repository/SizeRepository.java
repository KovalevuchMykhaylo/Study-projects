package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.ModelServo;
import ua.com.servo.entity.RotationAngle;
import ua.com.servo.entity.Size;

public interface SizeRepository extends JpaRepository<Size, Integer>, JpaSpecificationExecutor<Size>{
	Size findBySize(String size);
	
	@Query("SELECT a FROM Size a LEFT JOIN FETCH a.modelServo")
	List<Size> findAll();
	
	@Query(value = "SELECT f FROM Size f", countQuery = "SELECT count(f.id) FROM Size f")
	Page<Size> findAll(Pageable pageable);

}
