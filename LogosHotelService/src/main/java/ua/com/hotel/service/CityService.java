package ua.com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.hotel.dto.filter.SimpleFilter;
import ua.com.hotel.entity.City;

public interface CityService {
	void save(City city);

	List<City> findAll();

	City findOne(Long id);

	void delete(Long id);

	City findByName(String name);
	
	Page<City> findAll(Pageable pageable, SimpleFilter filter);
}
