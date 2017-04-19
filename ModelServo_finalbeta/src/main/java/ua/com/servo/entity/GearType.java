package ua.com.servo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//���� true ��  �����, ���� False �������
@Entity
public class GearType {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column
	private String gearType;
	@OneToMany(mappedBy="gearType")
	private List<ModelServo> modelServo = new ArrayList<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGearType() {
		return gearType;
	}
	public void setGearType(String gearType) {
		this.gearType = gearType;
	}
	public List<ModelServo> getModelServo() {
		return modelServo;
	}
	public void setModelServo(List<ModelServo> modelServo) {
		this.modelServo = modelServo;
	}

}
