package ua.com.hotel.serviceImp;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.hotel.dao.UserDao;
import ua.com.hotel.dto.filter.UserFilter;
import ua.com.hotel.entity.Role;
import ua.com.hotel.entity.User;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.service.UserService;
import ua.com.hotel.service.FileWriter.Folder;
import ua.com.hotel.service.FileWriter;
import ua.com.hotel.specification.UserSpecification;

@Service("userDetailsService")
public class UserServiceImp implements UserService, UserDetailsService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private FileWriter fileWriter;

	@Autowired
	private HotelNameService hotelNameService;

	@Override
	public void save(User user) {
		MultipartFile file = user.getFile();
		user = userDao.saveAndFlush(user);
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		if (fileWriter.write(Folder.USERS, file, user.getId())) {
			user.setVersion(user.getVersion() + 1);
		}
		userDao.save(user);
	}

	public void saveSubUser(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_SUBADMIN);
		userDao.save(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findOne(Long id) {
		return userDao.findOne(id);
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	@Override
	public User findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return userDao.findByEmail(username);
	}

	@PostConstruct
	public void addAdmin() {
		User user = userDao.findByEmail("admin");
		if (user == null) {
			user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			userDao.save(user);
		}
	}

	@Override
	public Page<User> findAll(Pageable pageable, UserFilter filter) {
		return userDao.findAll(new UserSpecification(filter), pageable);
	}
//Відправка листа користувачу
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
}
