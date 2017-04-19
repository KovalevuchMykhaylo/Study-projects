package ua.com.servo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//��� ��������
@Entity
public class RotationAngle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private int angle;
	@OneToMany(mappedBy="rotationAngle")
	private List<ModelServo> modelServo = new ArrayList<>();
	public RotationAngle(int id, int angle, List<ModelServo> modelServo) {
		super();
		this.id = id;
		this.angle = angle;
		this.modelServo = modelServo;
	}
	public RotationAngle(int angle, List<ModelServo> modelServo) {
		super();
		this.angle = angle;
		this.modelServo = modelServo;
	}
	public RotationAngle(int angle) {
		super();
		this.angle = angle;
	}
	public RotationAngle() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAngle() {
		return angle;
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	public List<ModelServo> getModelServo() {
		return modelServo;
	}
	public void setModelServo(List<ModelServo> modelServo) {
		this.modelServo = modelServo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RotationAngle other = (RotationAngle) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
