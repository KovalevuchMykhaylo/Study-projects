package ua.com.hotel.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.hotel.dao.CityDao;
import ua.com.hotel.dto.filter.SimpleFilter;
import ua.com.hotel.entity.City;
import ua.com.hotel.service.CityService;
import ua.com.hotel.service.FileWriter;
import ua.com.hotel.service.FileWriter.Folder;

@Service
public class CityServiceImp implements CityService {
	@Autowired
	private CityDao cityDao;
	@Autowired
	private FileWriter fileWriter;

	public void save(City city) {
		MultipartFile file = city.getFile();
		city = cityDao.saveAndFlush(city);
		if(fileWriter.write(Folder.CITY, file, city.getId())){
			city.setVersion(city.getVersion()+1);
			cityDao.save(city);
		}
	}

	public List<City> findAll() {
		return cityDao.findAll();
	}

	public City findOne(Long id) {
		return cityDao.findOne(id);
	}

	public void delete(Long id) {
		cityDao.delete(id);
	}

	@Override
	public City findByName(String name) {
		return cityDao.findByName(name);
	}

	@Override
	public Page<City> findAll(Pageable pageable, SimpleFilter filter) {
		return cityDao.findAll(findByNameLike(filter), pageable);
	}

	private Specification<City> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty())return null;
			return cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
