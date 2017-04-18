package ua.com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.hotel.dto.filter.AditionalServiceFilter;
import ua.com.hotel.entity.AditionalService;
import ua.com.hotel.entity.HotelName;

public interface AditionalServiceService {
	void save(AditionalService aditionalService);

	List<AditionalService> findAll();

	AditionalService findOne(Long id);

	void delete(Long id);

	AditionalService findByType(String type);

	AditionalService findUnique(String type, HotelName hotelName);
	
	Page<AditionalService> findAll(Pageable pageable, AditionalServiceFilter filter);

	List<AditionalService> findByHotelNameId(Long id);
}
