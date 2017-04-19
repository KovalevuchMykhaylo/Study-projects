package ua.com.servo.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import ua.com.servo.entity.Role.*;

@Entity
//@Table(name="_user", indexes=@Index(columnList = "_name"))
public class User implements UserDetails{

	private static final long serialVersionUID = 6463569735813971736L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
//	@Column(name="_name")
	private String email;

	private String password;
	@Enumerated
	@Column(name="_role")
	private Role role;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private ShopingCart shopingCart;

//	@ManyToOne(fetch = FetchType.LAZY)
//	private City city;
//	@ManyToMany
//	@JoinTable(name = "user_modelservo", joinColumns = @JoinColumn(name = "id_modelservo"), inverseJoinColumns = @JoinColumn(name = "id_user"))
//	private List<ModelServo> modelservo = new ArrayList<ModelServo>();
	
	public User() {
		super();
	}
	
	public ShopingCart getShopingCart() {
		return shopingCart;
	}

	public void setShopingCart(ShopingCart shopingCart) {
		this.shopingCart = shopingCart;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public City getCity() {
//		return city;
//	}
//	public void setCity(City city) {
//		this.city = city;
//	}
	
public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	//	public List<ModelServo> getModelservo() {
//		return modelservo;
//	}
//	public void setModelservo(List<ModelServo> modelservo) {
//		this.modelservo = modelservo;
//	}
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
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + id;
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		User other = (User) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}
	

}
