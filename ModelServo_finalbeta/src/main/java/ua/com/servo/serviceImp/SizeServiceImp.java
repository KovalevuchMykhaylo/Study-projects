package ua.com.servo.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.entity.Size;
import ua.com.servo.repository.SizeRepository;
import ua.com.servo.service.SizeService;
@Service
public class SizeServiceImp implements SizeService{

	@Autowired
	SizeRepository sizeRepository;
	
	@Override
	public void save(Size size) {
		sizeRepository.save(size);
	}

	@Override
	public List<Size> findAll() {
		return sizeRepository.findAll();
	}

	@Override
	public Size findOne(int id) {
		return sizeRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		sizeRepository.delete(id);
	}

	@Override
	public Size findBySize(String size) {
		return sizeRepository.findBySize(size);
	}

	@Override
	public Page<Size> findAll(Pageable pegeable, SimpleFilter filter) {
		return sizeRepository.findAll(findBySizeLike(filter), pegeable);
	}
	private Specification<Size> findBySizeLike (SimpleFilter filter){
	return (root, query, cb)->{
		if (filter.getSearch().isEmpty()) return null;
		return cb.like(cb.lower(root.get("size")), filter.getSearch().toLowerCase()+"%");
	};
	}
}
