package ua.com.hotel.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.com.hotel.dao.AditionalServiceDao;
import ua.com.hotel.dto.filter.AditionalServiceFilter;
import ua.com.hotel.entity.AditionalService;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.service.AditionalServiceService;
import ua.com.hotel.specification.AditionalServiceSpecification;

@Service
public class AditionalServiceServiceImp implements AditionalServiceService{

	@Autowired
	private AditionalServiceDao aditionalServiceDao;
	
	public void save(AditionalService aditionalService) {
		aditionalService.getType().toUpperCase();
		aditionalServiceDao.save(aditionalService);
	}

	public List<AditionalService> findAll() {
		return aditionalServiceDao.findAll();
	}

	public AditionalService findOne(Long id) {
		return aditionalServiceDao.findOne(id);
	}

	public void delete(Long id) {
		aditionalServiceDao.delete(id);
	}

	@Override
	public AditionalService findByType(String type) {
		return aditionalServiceDao.findByType(type);
	}

	@Override
	public AditionalService findUnique(String type, HotelName hotelName) {
		return aditionalServiceDao.findUnique(type, hotelName.getId());
	}

	@Override
	public Page<AditionalService> findAll(Pageable pageable, AditionalServiceFilter filter) {
		return aditionalServiceDao.findAll(new AditionalServiceSpecification(filter), pageable);
	}

	@Override
	public List<AditionalService> findByHotelNameId(Long id) {
		return aditionalServiceDao.findByHotelNameId(id);
	}

}
