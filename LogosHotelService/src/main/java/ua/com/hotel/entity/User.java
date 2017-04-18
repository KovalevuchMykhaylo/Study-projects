package ua.com.hotel.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "_user", indexes={@Index(columnList = "name, email, password, phoneNumber")})
public class User implements UserDetails{
	
	private static final long serialVersionUID = 8418747237103771076L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String email;
	@Column
	private String phoneNumber;
	@Column
	private String password;
	@OneToMany(mappedBy = "user")
	private List <RentDate> rentDates = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	private List <HotelName> hotelNames = new ArrayList<>();
	@Enumerated
	@Column(name="_role")
	private Role role;
	
	private int version;
	@Transient
	private MultipartFile file;
	
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Role getRole() {
		return role;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(role.name())));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public void setRole(Role role) {
		this.role = role;
	}

	public User(Long id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public User() {
		super();
	}
	

	public List<RentDate> getRentDates() {
		return rentDates;
	}
	public void setRentDates(List<RentDate> rentDates) {
		this.rentDates = rentDates;
	}
	public List<HotelName> getHotelNames() {
		return hotelNames;
	}
	public void setHotelNames(List<HotelName> hotelNames) {
		this.hotelNames = hotelNames;
	}
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
