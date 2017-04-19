package ua.com.servo.service;

import java.util.List;

import ua.com.servo.entity.GearType;

public interface GearTypeService {
	void save(GearType gearType);

	List<GearType> findAll();

	GearType findOne(int id);

	void delete(int id);

	GearType findByType(String type);

}
