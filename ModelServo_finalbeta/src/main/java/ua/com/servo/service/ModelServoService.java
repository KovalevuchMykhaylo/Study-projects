package ua.com.servo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.servo.dto.filter.ModelServoFilter;
import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.ModelServoForm;
import ua.com.servo.entity.ModelServo;

public interface ModelServoService {
	void save(ModelServoForm modelServoForm);

	List<ModelServo> findAll();

	ModelServo findOne(int id);

	void delete(int id);

	ModelServo findByModelServo (String name);

	Page<ModelServo> findAll(Pageable pegeable, ModelServoFilter filter);

	ModelServo findByName(String name);
	
	ModelServoForm findForm(Integer id);
	
	int findCount(int id);

	List<ModelServo> findByUserId(int userId);
}
