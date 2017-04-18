package ua.com.hotel.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.hotel.entity.City;


public interface CityDao extends JpaRepository<City, Long>, JpaSpecificationExecutor<City>{
	City findByName (String name);
	
}
