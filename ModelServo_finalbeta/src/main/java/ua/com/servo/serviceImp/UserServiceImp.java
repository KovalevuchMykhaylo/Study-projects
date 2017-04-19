package ua.com.servo.serviceImp;

import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.servo.entity.ModelServo;
import ua.com.servo.entity.Role;
import ua.com.servo.entity.ShopingCart;
import ua.com.servo.entity.User;
import ua.com.servo.repository.ModelServoRepository;
import ua.com.servo.repository.ShopingCartRepository;
import ua.com.servo.repository.UserRepository;
import ua.com.servo.service.UserService;

@Service("userDetailsService")
public class UserServiceImp implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private ShopingCartRepository shopingCartRepository;
	
	@Autowired
	private ModelServoRepository modelServoRepository;
	
	@Override
	public void save(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(int id) {
		return userRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		userRepository.delete(id);
	}

	@Override
	public User findByName(String name) {
		return findByName(name);
	}
	
	@PostConstruct
	public void addAdmin(){
		User user = userRepository.findByEmail("admin");
		if(user==null){
			user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			userRepository.save(user);
		}
}
	@Override
	@Transactional
	public void addToShoppingCart(int userId, int modelServoId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		if(cart==null){
			cart = shopingCartRepository.save(new ShopingCart());
			user.setShopingCart(cart);
		}
		ModelServo modelServo = modelServoRepository.findOne(modelServoId);
		cart.add(modelServo);
	}
	
	@Override
	@Transactional
	public void removeToShoppingCart(int userId, int modelServoId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		ModelServo modelServo = modelServoRepository.findOne(modelServoId);
		cart.remove(modelServo);
	}
	
	@Override
	@Transactional
	public void removeAllToShoppingCart(int userId) {
		User user = userRepository.findOne(userId);
		ShopingCart cart = user.getShopingCart();
		List<ModelServo> modelServo = modelServoRepository.findAll();
		cart.removeAll(modelServo);
	}
	
	
	@Override
	public int createNewUser() {
		return userRepository.saveAndFlush(new User()).getId();
	}
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void setEncoder(BCryptPasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public void setShopingCartRepository(ShopingCartRepository shopingCartRepository) {
		this.shopingCartRepository = shopingCartRepository;
	}

	public void setModelServoRepository(ModelServoRepository modelServoRepository) {
		this.modelServoRepository = modelServoRepository;
}
	@Override
	public void sendMail(String content, String email, String mailBody) {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		Session session = Session.getDefaultInstance(properties,
				new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								"kovalevuch.c.h@gmail.com", "idkfaiddqd");
					}
				});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("kovalevuch.c.h@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					email));
			message.setSubject(content, "UTF-8");
			message.setText(mailBody);
			Transport.send(message);
		} catch (MessagingException е) {
			е.printStackTrace();
		}
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
