package ua.com.servo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class City {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;
@Column
private String name;

//@OneToMany(mappedBy="city")
//private List<User> user = new ArrayList<>();

public City() {
	super();
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

//public List<User> getUser() {
//	return user;
//}
//
//public void setUser(List<User> user) {
//	this.user = user;
//}

}
