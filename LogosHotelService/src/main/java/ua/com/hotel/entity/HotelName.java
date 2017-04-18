package ua.com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//@NamedQueries({
//	@NamedQuery(name = "findAllHotelName", query = "select a from HotelName a"),
//	@NamedQuery(name = "findOneHotelName", query = "select a from HotelName a.name =:param"),
//})
//Номер номера, кількість кімнат в номері, ціна номера, назва готелю
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "hotel_name", indexes = @Index(columnList = "name, adress, phoneNumber, eMail"))
public class HotelName extends AbstractEntity{
	@Column(name = "name")
	private String name;
	@Column(name = "adress")
	private String adress;
	@Column(name = "phoneNumber")
	private String phoneNumber;
	@Column(name = "eMail")
	private String eMail;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_city")
	private City city;
	@OneToMany(mappedBy = "hotelName")
	private List<AditionalService> aditionalServices = new ArrayList<>();
	@OneToMany(mappedBy = "hotelName")
	private List<RoomService> roomServices = new ArrayList<>();
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	private int version;
	@Transient
	private MultipartFile file;
	public HotelName() {
	}
	public String getName() {
		return name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public List<AditionalService> getAditionalServices() {
		return aditionalServices;
	}
	public void setAditionalServices(List<AditionalService> aditionalServices) {
		this.aditionalServices = aditionalServices;
	}
	public List<RoomService> getRoomServices() {
		return roomServices;
	}
	public void setRoomServices(List<RoomService> roomServices) {
		this.roomServices = roomServices;
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
	
}
