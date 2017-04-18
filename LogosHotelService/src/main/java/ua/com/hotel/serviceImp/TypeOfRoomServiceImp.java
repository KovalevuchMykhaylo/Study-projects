package ua.com.hotel.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.hotel.dao.TypeOfRoomDao;
import ua.com.hotel.dto.filter.SimpleFilter;
import ua.com.hotel.entity.TypeOfRoom;
import ua.com.hotel.service.TypeOfRoomService;
@Service
public class TypeOfRoomServiceImp implements TypeOfRoomService{

		@Autowired
		private TypeOfRoomDao typeOfRoomDao;

		public void save(TypeOfRoom typeOfRoom) {
			 typeOfRoom.getType().toUpperCase();
			typeOfRoomDao.save(typeOfRoom);
		}

		public List<TypeOfRoom> findAll() {
			return typeOfRoomDao.findAll();
		}

		public TypeOfRoom findOne(Long id) {
			return typeOfRoomDao.findOne(id);
		}

		public void delete(Long id) {
			typeOfRoomDao.delete(id);
		}

		@Override
		public TypeOfRoom findByType(String type) {
			return typeOfRoomDao.findByType(type);
		}

		@Override
		public Page<TypeOfRoom> findAll(Pageable pageable, SimpleFilter filter) {
			return typeOfRoomDao.findAll(findByNameLike(filter), pageable);
		}

		private Specification<TypeOfRoom> findByNameLike(SimpleFilter filter) {
			return (root, query, cb)->{
				if(filter.getSearch().isEmpty())return null;
				return cb.like(cb.lower(root.get("type")), filter.getSearch().toLowerCase()+"%");
			};
		}
}
