package ua.com.servo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class ShopingCart {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@OneToMany(mappedBy="shopingCart")
	private List<User> users = new ArrayList<>();
	@ManyToMany
	private List<ModelServo> modelServos = new ArrayList<>();
	@Column(name="_count")
	private int count;
	
	public void add(ModelServo e) {
		modelServos.add(e);
		count = modelServos.size();
	}
	
	public void remove(ModelServo e) {
		modelServos.remove(e);
		count = modelServos.size();
	}
	
	public void removeAll(List<ModelServo> modelServo) {
		modelServos.removeAll(modelServos);
		count = modelServos.size();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<ModelServo> getModelServos() {
		return modelServos;
	}
	public void setModelServo(List<ModelServo> modelServos) {
		this.modelServos = modelServos;
	}
}