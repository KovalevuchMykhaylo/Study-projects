package ua.com.servo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Weight {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int weight;
	
	@OneToMany(mappedBy="weight")
	private List<ModelServo> modelServo;

	public Weight() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public List<ModelServo> getModelServo() {
		return modelServo;
	}

	public void setModelServo(List<ModelServo> modelServo) {
		this.modelServo = modelServo;
	}
	
	
	
}
