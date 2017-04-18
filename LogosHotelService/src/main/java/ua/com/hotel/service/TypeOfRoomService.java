package ua.com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.hotel.dto.filter.SimpleFilter;
import ua.com.hotel.entity.TypeOfRoom;

public interface TypeOfRoomService {
	void save(TypeOfRoom typeOfRoom);

	List<TypeOfRoom> findAll();

	TypeOfRoom findOne(Long id);

	void delete(Long id);

	TypeOfRoom findByType(String type);

	Page <TypeOfRoom> findAll(Pageable pageable, SimpleFilter filter);
}
