package ua.com.servo.dto.form;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.springframework.web.multipart.MultipartFile;

import ua.com.servo.entity.ForceShaft;
import ua.com.servo.entity.GearType;
import ua.com.servo.entity.PowerVoltage;
import ua.com.servo.entity.RotationAngle;
import ua.com.servo.entity.Size;
import ua.com.servo.entity.SpeedRotation;
import ua.com.servo.entity.Type;
import ua.com.servo.entity.Weight;

public class ModelServoForm {
	
	private int id;

	private String name;
	
	private String price;

	private ForceShaft forceShaft;

	private SpeedRotation speedRotation;

	private Type type;

	private PowerVoltage powerVoltage;
	
	private RotationAngle rotationAngle;
	
	private Weight weight;
	
	private GearType gearType;
	
	private Size size;
	
	private int version;

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public ForceShaft getForceShaft() {
		return forceShaft;
	}

	public void setForceShaft(ForceShaft forceShaft) {
		this.forceShaft = forceShaft;
	}

	public SpeedRotation getSpeedRotation() {
		return speedRotation;
	}

	public void setSpeedRotation(SpeedRotation speedRotation) {
		this.speedRotation = speedRotation;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public PowerVoltage getPowerVoltage() {
		return powerVoltage;
	}

	public void setPowerVoltage(PowerVoltage powerVoltage) {
		this.powerVoltage = powerVoltage;
	}

	public RotationAngle getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(RotationAngle rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public GearType getGearType() {
		return gearType;
	}

	public void setGearType(GearType gearType) {
		this.gearType = gearType;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

}
