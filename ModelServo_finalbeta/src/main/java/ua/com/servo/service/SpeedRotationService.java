package ua.com.servo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.SpeedRotationForm;
import ua.com.servo.entity.SpeedRotation;

public interface SpeedRotationService {
	void save(SpeedRotationForm speedRotationForm);

	List<SpeedRotation> findAll();

	SpeedRotation findOne(int id);

	void delete(int id);

	SpeedRotation findByspeedRotation(Double speedRotation);
	
	Page <SpeedRotation> findAll(Pageable pegeable, SimpleFilter filter);

	SpeedRotationForm findForm(Integer id);
}
