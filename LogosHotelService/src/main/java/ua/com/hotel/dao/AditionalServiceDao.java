package ua.com.hotel.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.hotel.entity.AditionalService;

public interface AditionalServiceDao extends JpaRepository<AditionalService, Long>, JpaSpecificationExecutor<AditionalService>{
	AditionalService findByType (String type);
	@Query("SELECT a FROM AditionalService a LEFT JOIN FETCH a.hotelName")
	List<AditionalService> findAll();
	@Query("SELECT a FROM AditionalService a LEFT JOIN FETCH a.hotelName WHERE a.id=?1")
	List<AditionalService> findAll(Long id);
	@Query("SELECT i FROM AditionalService i WHERE i.hotelName.id = ?1")
	List<AditionalService> findByHotelNameId(Long id);
	@Query("SELECT a FROM AditionalService a LEFT JOIN FETCH a.hotelName WHERE a.id=?1")
	AditionalService findOne(Long id);
	@Query("SELECT a FROM AditionalService a WHERE a.type=?1 and a.hotelName.id=?2")
	AditionalService findUnique(String type, Long hotelNameId);
	@Query(value="SELECT a FROM AditionalService a LEFT JOIN FETCH a.hotelName", countQuery="SELECT count(a.id) FROM AditionalService a")
	Page<AditionalService> findAll(Pageable pageable);
}
