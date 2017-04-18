package ua.com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.hotel.dto.filter.HotelNameFilter;
import ua.com.hotel.entity.City;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.User;

public interface HotelNameService {
	
	void save(HotelName hotelName);

	List<HotelName> findAll();

	HotelName findOne(Long id);

	void delete(Long id);

	HotelName findByName(String name);
	
	List<HotelName> findByCityId(Long id);

	HotelName findUnique(String name, City city, User email);
	
	Page<HotelName> findAll(Pageable pageable, HotelNameFilter filter);

	List <HotelName> findAllByUser();

	List <HotelName> findAllBySubUser(Long id);

//	List<HotelName> findHotelNameByRentDate();
}
