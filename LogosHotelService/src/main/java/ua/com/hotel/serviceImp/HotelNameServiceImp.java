package ua.com.hotel.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.hotel.dao.HotelNameDao;
import ua.com.hotel.dto.filter.HotelNameFilter;
import ua.com.hotel.entity.City;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.User;
import ua.com.hotel.service.FileWriter;
import ua.com.hotel.service.FileWriter.Folder;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.specification.HotelNameSpecification;

@Service
public class HotelNameServiceImp implements HotelNameService {

	@Autowired
	private HotelNameDao hotelNameDao;
	@Autowired
	private FileWriter fileWriter;

	public void save(HotelName entity) {
//		HotelName entity = new HotelName();
		MultipartFile file = entity.getFile();
		entity = hotelNameDao.saveAndFlush(entity);
		if(fileWriter.write(Folder.HOTELNAME, file, entity.getId())){
			entity.setVersion(entity.getVersion()+1);
			hotelNameDao.save(entity);
		}
	}

	public List<HotelName> findAll() {
		return hotelNameDao.findAll();
	}

	public HotelName findOne(Long id) {
		return hotelNameDao.findOne(id);
	}

	public void delete(Long id) {
		hotelNameDao.delete(id);
	}

	@Override
	public HotelName findByName(String name) {
		return hotelNameDao.findByName(name);
	}

	@Override
	public List<HotelName> findByCityId(Long id) {
		return hotelNameDao.findByCityId(id);
	}


	@Override
	public Page<HotelName> findAll(Pageable pageable, HotelNameFilter filter) {
		return hotelNameDao.findAll(new HotelNameSpecification(filter), pageable);
	}

	@Override
	public HotelName findUnique(String name, City city, User email) {
		return hotelNameDao.findUnique(name, city.getId(), email.getId());
	}

	@Override
	public List<HotelName> findAllByUser() {
		return hotelNameDao.findAllByUser();
	}

	@Override
	public List<HotelName> findAllBySubUser(Long id) {
		return hotelNameDao.findAllBySubUser(id);
	}

}
