package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.SpeedRotationForm;
import ua.com.servo.entity.SpeedRotation;
import ua.com.servo.repository.SpeedRotationRepository;
import ua.com.servo.service.SpeedRotationService;
@Service
public class SpeedRotationServiceImp implements SpeedRotationService{
	
	@Autowired
	SpeedRotationRepository speedRotationRepository;

	@Override
	public void save(SpeedRotationForm form) {
		SpeedRotation entity = new SpeedRotation();
		entity.setSpeedRotation(new Double((form.getSpeedRotation())));
		entity.setId(form.getId());
		speedRotationRepository.save(entity);
	}

	@Override
	public List<SpeedRotation> findAll() {
		return speedRotationRepository.findAll();
	}

	@Override
	public SpeedRotation findOne(int id) {
		return speedRotationRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		speedRotationRepository.delete(id);
	}

	@Override
	public SpeedRotation findByspeedRotation(Double speedRotation) {
		
		return speedRotationRepository.findBySpeedRotation(speedRotation);
	}

	@Override
	public Page<SpeedRotation> findAll(Pageable pegeable, SimpleFilter filter) {
		return speedRotationRepository.findAll(findByspeedRotationLike(filter), pegeable);
	}
	
	

	private Specification<SpeedRotation> findByspeedRotationLike (SimpleFilter filter){
	return (root, query, cb)->{
		if (filter.getSearch().isEmpty()) return null;
		return cb.like(cb.lower(root.get("speedRotation")), filter.getSearch().toLowerCase()+"%");
	};
}
	

	@Override
	public SpeedRotationForm findForm(Integer id) {
		SpeedRotation entity = findOne(id);
		SpeedRotationForm form = new SpeedRotationForm();
		form.setSpeedRotation(entity.getSpeedRotation());
		form.setId(entity.getId());
		return form;
	}

	

}
