package ua.com.hotel.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.hotel.dto.form.RentDateForm;
import ua.com.hotel.entity.RentDate;

public interface RentDateService {
	
	void save(RentDateForm rentDate);

	List<RentDate> findAll();

	RentDate findOne(Long id);
	
	RentDateForm finForm(Long id);

	void delete(Long id);

	Page<RentDate> findAll(Pageable pageable);

	List <RentDate> findAllByUser(Long id);

	List <RentDate> findDateByRoomService(LocalDate first, LocalDate second, Long id);

}
