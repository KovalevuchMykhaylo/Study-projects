package ua.com.hotel.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.hotel.entity.User;

public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User>{
	
	User findByEmail(String username);
	
	User findByName (String name);
	
	@Query(value="SELECT a FROM User a LEFT JOIN FETCH a.hotelNames", countQuery="SELECT count(a.id) FROM User a")
	Page<User> findAll(Pageable pageable);

}
