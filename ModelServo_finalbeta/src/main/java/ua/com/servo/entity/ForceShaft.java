package ua.com.servo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//������� �� ���� 
@Entity
public class ForceShaft {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private double forceShaft;
	@OneToMany(mappedBy="forceShaft")
	private List<ModelServo> modelServo = new ArrayList<>();
	public ForceShaft() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getForceShaft() {
		return forceShaft;
	}
	public void setForceShaft(double forceShaft) {
		this.forceShaft = forceShaft;
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
		ForceShaft other = (ForceShaft) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
