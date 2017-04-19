package ua.com.servo.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class ModelServo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String name;
	
	private BigDecimal price;

//	@ManyToMany
//	@JoinTable(name = "user_modelservo", joinColumns = @JoinColumn(name = "id_user"), 
//	inverseJoinColumns = @JoinColumn(name = "id_modelservo"))
//	private List<User> users =new ArrayList<User>();
	
	@ManyToMany(mappedBy="modelServos")
	private List<ShopingCart> shopingCarts = new ArrayList<>();

	@ManyToOne (fetch=FetchType.LAZY)
	private ForceShaft forceShaft;

	@ManyToOne (fetch=FetchType.LAZY)
	private SpeedRotation speedRotation;

	@ManyToOne(fetch=FetchType.LAZY)
	private Type type;

	@ManyToOne(fetch=FetchType.LAZY)
	private PowerVoltage powerVoltage;
	

	@ManyToOne(fetch=FetchType.LAZY)
	private RotationAngle rotationAngle;
	
	@ManyToOne
	private Weight weight;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private GearType gearType;
	
	@ManyToOne (fetch=FetchType.LAZY)
	private Size size;
	
	private int version;
	@Transient
	private MultipartFile file;
	
	public List<ShopingCart> getShopingCarts() {
		return shopingCarts;
	}

	public void setShopingCarts(List<ShopingCart> shopingCarts) {
		this.shopingCarts = shopingCarts;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public List<User> getUsers() {
//		return users;
//	}

//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

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
		ModelServo other = (ModelServo) obj;
		if (id != other.id)
			return false;
		return true;
	}


}
