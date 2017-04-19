package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.SpeedRotationForm;
import ua.com.servo.dto.form.WeightForm;
import ua.com.servo.entity.SpeedRotation;
import ua.com.servo.entity.Weight;
import ua.com.servo.repository.WeightRepository;
import ua.com.servo.service.WeightService;

@Service
public class WeightServiceImp implements WeightService{
	
	@Autowired
	WeightRepository weightRepository;

	@Override
	public void save(WeightForm form) {
		Weight entity = new Weight();
		entity.setWeight(new Integer((form.getWeight())));
		entity.setId(form.getId());
		weightRepository.save(entity);
	}

	@Override
	public List<Weight> findAll() {
		return weightRepository.findAll();
	}

	@Override
	public Weight findOne(int id) {
		return weightRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		weightRepository.delete(id);		
	}

	@Override
	public Weight findByweight(Integer weight) {
		return weightRepository.findByweight(weight);
	}

	private Specification<Weight> findByweightLike (SimpleFilter filter){
		return (root, query, cb)->{
			if (filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("weight")), filter.getSearch().toLowerCase()+"%");
		};
	}
		

		@Override
		public WeightForm findForm(Integer id) {
			Weight entity = findOne(id);
			WeightForm form = new WeightForm();
			form.setWeight(entity.getWeight());
			form.setId(entity.getId());
			return form;
		}

		@Override
		public Page<Weight> findAll(Pageable pegeable, SimpleFilter filter) {
			return weightRepository.findAll(findByweightLike(filter), pegeable);
		}

		

}
