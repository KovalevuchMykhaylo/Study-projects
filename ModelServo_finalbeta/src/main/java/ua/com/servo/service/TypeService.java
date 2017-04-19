package ua.com.servo.service;

import java.util.List;

import ua.com.servo.entity.Type;

public interface TypeService {
	void save(Type type);

	List<Type> findAll();

	Type findOne(int id);

	void delete(int id);
	
	Type findByType(String type);
}
