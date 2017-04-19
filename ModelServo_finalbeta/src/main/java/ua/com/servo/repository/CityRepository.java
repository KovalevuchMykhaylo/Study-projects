package ua.com.servo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.com.servo.entity.City;
import ua.com.servo.entity.User;

public interface CityRepository extends JpaRepository<City, Integer>{
	City findByName(String name);
//	@Query("SELECT i FROM User i WHERE i.city.id = ?1")
//	List <User> findByName (int id);
//	@Query("SELECT i FROM User i WHERE i.city.id = ?1")
//	List <User> findBySurName (int id);
}
