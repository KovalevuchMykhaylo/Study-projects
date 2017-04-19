package ua.com.servo.service;

import java.util.List;

import ua.com.servo.entity.PowerVoltage;

public interface PowerVoltageService {
	void save(PowerVoltage powerVoltage);

	List<PowerVoltage> findAll();

	PowerVoltage findOne(int id);

	void delete(int id);

	PowerVoltage findByPowerVoltage(String powerVoltage);
	
}
