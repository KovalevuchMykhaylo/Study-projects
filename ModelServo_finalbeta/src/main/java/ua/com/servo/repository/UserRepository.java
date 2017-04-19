package ua.com.servo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.servo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String username);
	
}
