package ua.com.servo.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//�������� ��������
@Entity
public class SpeedRotation {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private double speedRotation;
	
	@OneToMany(mappedBy="speedRotation")
	private List<ModelServo> modelServo;

	public SpeedRotation() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSpeedRotation() {
		return speedRotation;
	}

	public void setSpeedRotation(double speedRotation) {
		this.speedRotation = speedRotation;
	}

	public List<ModelServo> getModelServo() {
		return modelServo;
	}

	public void setModelServo(List<ModelServo> modelServo) {
		this.modelServo = modelServo;
	}

	



	
	
	
	
}
