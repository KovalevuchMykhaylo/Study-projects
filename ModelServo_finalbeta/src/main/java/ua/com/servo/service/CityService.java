package ua.com.servo.service;

import java.util.List;

import ua.com.servo.entity.City;

public interface CityService {
	void save(City city);

	List<City> findAll();

	City findOne(int id);

	void delete(int id);

	City findByName(String type);
}
