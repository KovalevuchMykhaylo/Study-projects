package ua.com.servo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.entity.ForceShaft;


public interface ForceShaftService {
	void save(ForceShaftForm forceShaft);

	List<ForceShaft> findAll();

	ForceShaft findOne(int id);

	void delete(int id);

	ForceShaft findByforceShaft(Double forceShaft);

	Page <ForceShaft> findAll(Pageable pegeable, SimpleFilter filter);

	ForceShaftForm findForm(Integer id);
}
