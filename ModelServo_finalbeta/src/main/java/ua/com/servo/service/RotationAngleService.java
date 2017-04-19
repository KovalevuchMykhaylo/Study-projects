package ua.com.servo.service;

import java.util.List;

import ua.com.servo.dto.form.RotationAngleForm;
import ua.com.servo.entity.RotationAngle;

public interface RotationAngleService {
	void save(RotationAngleForm rotationAngleForm);

	List<RotationAngle> findAll();

	RotationAngle findOne(int id);

	void delete(int id);

	RotationAngle findByAngle(int angle);
	
	RotationAngleForm findForm(Integer id);
}
