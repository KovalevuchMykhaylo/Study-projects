package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.entity.ForceShaft;

import ua.com.servo.repository.ForceShaftRepository;
import ua.com.servo.service.ForceShaftService;

@Service
public class ForceShaftServiceImp implements ForceShaftService{
	@Autowired
	private ForceShaftRepository forceShaftRepository;

	public void save(ForceShaftForm form) {
		ForceShaft entity = new ForceShaft();
		entity.setForceShaft(new Double((form.getForceShaft())));
		entity.setId(form.getId());
		forceShaftRepository.save(entity);
	}

	public List<ForceShaft> findAll() {
		return forceShaftRepository.findAll();
	}

	public ForceShaft findOne(int id) {
		return forceShaftRepository.findOne(id);
	}

	public void delete(int id) {
		forceShaftRepository.delete(id);
	}

	@Override
	public ForceShaft findByforceShaft(Double forceShaft) {
		return forceShaftRepository.findByforceShaft(forceShaft);
	}

	@Override
	public Page<ForceShaft> findAll(Pageable pegeable, SimpleFilter filter) {
				return forceShaftRepository.findAll(findByforceShaftLike(filter), pegeable);
		}
		private Specification<ForceShaft> findByforceShaftLike (SimpleFilter filter){
		return (root, query, cb)->{
			if (filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("forceShaft")), filter.getSearch().toLowerCase()+"%");
		};
	}

		@Override
		public ForceShaftForm findForm(Integer id) {
			ForceShaft entity = findOne(id);
			ForceShaftForm form = new ForceShaftForm();
			form.setForceShaft(entity.getForceShaft());
			form.setId(entity.getId());
			return form;
		}

}
