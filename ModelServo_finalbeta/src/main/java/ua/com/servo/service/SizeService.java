package ua.com.servo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.entity.Size;

public interface SizeService {
	void save(Size size);

	List<Size> findAll();

	Size findOne(int id);

	void delete(int id);

	Size findBySize(String size);

	Page<Size> findAll(Pageable pegeable, SimpleFilter filter);

}
