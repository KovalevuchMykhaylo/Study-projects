package ua.com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.hotel.dto.filter.SimpleFilter;
import ua.com.hotel.entity.TypeOfBathRoom;

public interface TypeOfBathRoomService {
	void save(TypeOfBathRoom typeOfBathRoom);

	List<TypeOfBathRoom> findAll();

	TypeOfBathRoom findOne(Long id);

	void delete(Long id);

	TypeOfBathRoom findByType(String type);

	Page<TypeOfBathRoom> findAll(Pageable pageable, SimpleFilter filter);
}
