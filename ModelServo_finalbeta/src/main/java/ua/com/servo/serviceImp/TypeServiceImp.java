package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.servo.entity.Type;
import ua.com.servo.repository.TypeRepository;
import ua.com.servo.service.TypeService;
@Service
public class TypeServiceImp implements TypeService{
	
	@Autowired
	TypeRepository typeRepository;

	@Override
	public void save(Type type) {
		typeRepository.save(type);
	}

	@Override
	public List<Type> findAll() {
		return typeRepository.findAll();
	}

	@Override
	public Type findOne(int id) {
		return typeRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		typeRepository.delete(id);
	}

	@Override
	public Type findByType(String type) {
		return typeRepository.findByType(type);
	}

}
