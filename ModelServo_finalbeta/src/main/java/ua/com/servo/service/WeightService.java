package ua.com.servo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.servo.dto.filter.SimpleFilter;

import ua.com.servo.dto.form.WeightForm;

import ua.com.servo.entity.Weight;

public interface WeightService {
	void save(WeightForm form);

	List<Weight> findAll();

	Weight findOne(int id);

	void delete(int id);
	
	Weight findByweight(Integer weight);
	
	Page <Weight> findAll(Pageable pegeable, SimpleFilter filter);

	WeightForm findForm(Integer id);

	

	
}
