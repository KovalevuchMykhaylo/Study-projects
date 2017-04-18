package ua.com.hotel.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.hotel.entity.RoomService;

public interface RoomServiceDao extends JpaRepository<RoomService, Long>, JpaSpecificationExecutor<RoomService>{
	@Override
	@Query("SELECT a FROM RoomService a LEFT JOIN FETCH a.hotelName LEFT JOIN FETCH a.typeOfBathRoom LEFT JOIN FETCH a.typeOfRoom")
	List<RoomService> findAll();
	
	@Query("SELECT a FROM RoomService a LEFT JOIN FETCH a.typeOfBathRoom LEFT JOIN FETCH a.typeOfRoom")
	List<RoomService> findAllRoomsServices();
	
	@Query("SELECT a FROM RoomService a LEFT JOIN FETCH a.hotelName LEFT JOIN FETCH a.typeOfBathRoom LEFT JOIN FETCH a.typeOfRoom WHERE a.id=?1")
	RoomService findOne(Long id);
	
	@Query("SELECT i FROM RoomService i WHERE i.hotelName.id = ?1")
	List<RoomService> findByHotelNameId(Long id);
	
	@Query("SELECT a FROM RoomService a WHERE a.price=?1 and a.room=?2 and a.roomNumber=?3 and a.hotelName.id=?4 and a.typeOfBathRoom.id=?5 and a.typeOfRoom.id=?6")
	RoomService findUnique(BigDecimal price, Integer room, Integer roomNumber, Long hotelNameId, Long typeOfBathRoomId, Long typeOfRoomid);
	//Пошук кімнат з їх характеристиками
	@Query(value="SELECT a FROM RoomService a LEFT JOIN FETCH a.hotelName LEFT JOIN FETCH a.typeOfRoom LEFT JOIN FETCH a.typeOfBathRoom", countQuery="SELECT count(a.id) FROM RoomService a")
	Page<RoomService> findAll(Pageable pageable);
}
