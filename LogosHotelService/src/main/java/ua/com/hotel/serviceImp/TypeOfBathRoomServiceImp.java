package ua.com.hotel.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.hotel.dao.TypeOfBathRoomDao;
import ua.com.hotel.dto.filter.SimpleFilter;
import ua.com.hotel.entity.TypeOfBathRoom;
import ua.com.hotel.service.TypeOfBathRoomService;
@Service
public class TypeOfBathRoomServiceImp implements TypeOfBathRoomService{
	@Autowired
	private TypeOfBathRoomDao typeOfBathRoomDao;

	public void save(TypeOfBathRoom typeOfBathRoom) {
		 typeOfBathRoom.getType().toUpperCase();
		typeOfBathRoomDao.save(typeOfBathRoom);
	}

	public List<TypeOfBathRoom> findAll() {
		return typeOfBathRoomDao.findAll();
	}

	public TypeOfBathRoom findOne(Long id) {
		return typeOfBathRoomDao.findOne(id);
	}

	public void delete(Long id) {
		typeOfBathRoomDao.delete(id);
	}

	@Override
	public TypeOfBathRoom findByType(String type) {
		return typeOfBathRoomDao.findByType(type);
	}

	@Override
	public Page<TypeOfBathRoom> findAll(Pageable pageable, SimpleFilter filter) {
		return typeOfBathRoomDao.findAll(findByNameLike(filter), pageable);
	}

	private Specification<TypeOfBathRoom> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty())return null;
			return cb.like(cb.lower(root.get("type")), filter.getSearch().toLowerCase()+"%");
		};
	}
}
