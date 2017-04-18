package ua.com.hotel.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.hotel.dto.filter.UserFilter;
import ua.com.hotel.entity.User;

public interface UserService {
	void save(User user);

	List<User> findAll();

	User findOne(Long id);

	void delete(Long id);

	User findByName(String name);

	User findByEmail(String email);

	void saveSubUser(User user);
	
	Page <User> findAll(Pageable pageable, UserFilter filter);
	
	public void sendMail(String content, String email, String mailBody);

}
