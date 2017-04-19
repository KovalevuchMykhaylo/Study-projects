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
public class Size {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "size")
	private String size;
	@OneToMany(mappedBy="size")
	private List<ModelServo> modelServo = new ArrayList<>();
	public Size() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<ModelServo> getModelServo() {
		return modelServo;
	}
	public void setModelServo(List<ModelServo> modelServo) {
		this.modelServo = modelServo;
	}
	
	
	
	
}
