package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.servo.entity.GearType;
import ua.com.servo.repository.GearTypeRepository;
import ua.com.servo.service.GearTypeService;
@Service
public class GearTypeServiceImp implements GearTypeService{

	@Autowired
	private GearTypeRepository gearTypeRepository;

	public void save(GearType GearType) {
		gearTypeRepository.save(GearType);
	}

	public List<GearType> findAll() {
		return gearTypeRepository.findAll();
	}

	public GearType findOne(int id) {
		return gearTypeRepository.findOne(id);
	}

	public void delete(int id) {
		gearTypeRepository.delete(id);
	}

	@Override
	public GearType findByType(String type) {
		return gearTypeRepository.findByGearType(type);
	}

}
