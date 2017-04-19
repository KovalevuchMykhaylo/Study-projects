package ua.com.servo.service;

import java.util.List;

import ua.com.servo.entity.User;


public interface UserService {
	void save(User user);

	List<User> findAll();

	User findOne(int id);

	void delete(int id);
	
	User findByName (String name);

	int createNewUser();

	void addToShoppingCart(int userId, int modelServoId);

	void removeToShoppingCart(int userId, int modelServoId);
	
	void removeAllToShoppingCart(int userId);
	
	public void sendMail(String content, String email, String mailBody);

	User findByEmail(String email);

}
