package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.dto.form.RotationAngleForm;
import ua.com.servo.entity.ForceShaft;
import ua.com.servo.entity.RotationAngle;
import ua.com.servo.repository.RotationAngleRepository;
import ua.com.servo.service.RotationAngleService;
@Service
public class RotationAngleServiceImp implements RotationAngleService {

	@Autowired
	private RotationAngleRepository rotationAngleRepository;
	@Override
	public void save(RotationAngleForm form) {
		RotationAngle entity = new RotationAngle();
		entity.setAngle(new Integer((form.getAngle())));
		entity.setId(form.getId());
		rotationAngleRepository.save(entity);
	}

	@Override
	public List<RotationAngle> findAll() {
			return rotationAngleRepository.findAll();
	}

	@Override
	public RotationAngle findOne(int id) {
		
		return rotationAngleRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		rotationAngleRepository.delete(id);
		
	}

	@Override
	public RotationAngle findByAngle(int angle) {
		return rotationAngleRepository.findByAngle (angle);
	}
	
	@Override
	public RotationAngleForm findForm(Integer id) {
		RotationAngle entity = findOne(id);
		RotationAngleForm form = new RotationAngleForm();
		form.setAngle(entity.getAngle());
		form.setId(entity.getId());
		return form;
	}

	

}
