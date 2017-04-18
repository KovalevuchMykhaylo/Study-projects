package ua.com.hotel.dto.filter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.com.hotel.entity.Role;

public class UserFilter {

	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	private String password;
	private List<Long> rentDatesId = new ArrayList<>();
	private List<Long> hotelNamesId = new ArrayList<>();
	private Role role;
	private int version;
	private MultipartFile file;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Long> getRentDatesId() {
		return rentDatesId;
	}
	public void setRentDatesId(List<Long> rentDatesId) {
		this.rentDatesId = rentDatesId;
	}
	public List<Long> getHotelNamesId() {
		return hotelNamesId;
	}
	public void setHotelNamesId(List<Long> hotelNamesId) {
		this.hotelNamesId = hotelNamesId;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}

}

