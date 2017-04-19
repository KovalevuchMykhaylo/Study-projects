package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.servo.entity.City;
import ua.com.servo.repository.CityRepository;
import ua.com.servo.service.CityService;

@Service
public class CityServiceImp implements CityService {
	@Autowired
	private CityRepository cityRepository;

	public void save(City city) {
		city.getName().toUpperCase();
		cityRepository.save(city);
	}

	public List<City> findAll() {
		return cityRepository.findAll();
	}

	public City findOne(int id) {
		return cityRepository.findOne(id);
	}

	public void delete(int id) {
		cityRepository.delete(id);
	}

	@Override
	public City findByName(String name) {
		return cityRepository.findByName(name);
	}

}

