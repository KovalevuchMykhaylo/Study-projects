package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.servo.entity.PowerVoltage;
import ua.com.servo.repository.PowerVoltageRepository;
import ua.com.servo.service.PowerVoltageService;
@Service
public class PowerVoltageServiceImp implements PowerVoltageService{

	@Autowired
	PowerVoltageRepository powerVoltageRepository;
	
	@Override
	public void save(PowerVoltage powerVoltage) {
		powerVoltageRepository.save(powerVoltage);
	}

	@Override
	public List<PowerVoltage> findAll() {
		return powerVoltageRepository.findAll();
	}

	@Override
	public PowerVoltage findOne(int id) {
		return powerVoltageRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		powerVoltageRepository.delete(id);
		
	}

	@Override
	public PowerVoltage findByPowerVoltage(String powerVoltage) {
		return powerVoltageRepository.findByPowerVoltage(powerVoltage);
	}

}