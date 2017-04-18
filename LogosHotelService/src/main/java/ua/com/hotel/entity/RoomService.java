package ua.com.hotel.entity;

import java.math.BigDecimal;
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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;
//Кімната
@Entity
@Table(name = "service", indexes = {
		@Index(columnList = "roomNumber, room, price"),
		@Index(columnList = "room"), @Index(columnList = "roomNumber"),
		@Index(columnList = "price") })
public class RoomService extends AbstractEntity {
	@Column(name = "roomNumber")
	private int roomNumber;
	@Column(name = "room")
	private int room;
	@Column(name = "price")
	private BigDecimal price;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_hotelHame")
	private HotelName hotelName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_typeOfBathRoom")
	private TypeOfBathRoom typeOfBathRoom;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_typeOfRoom")
	private TypeOfRoom typeOfRoom;
	@OneToMany(mappedBy = "roomService")
	private List<RentDate> rentDates = new ArrayList<>();

	private int version;
	@Transient
	private MultipartFile file;

	public List<RentDate> getRentDates() {
		return rentDates;
	}

	public void setRentDates(List<RentDate> rentDates) {
		this.rentDates = rentDates;
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

	public RoomService(int roomNumber, int room, BigDecimal price,
			HotelName hotelName, TypeOfBathRoom typeOfBathRoom,
			TypeOfRoom typeOfRoom) {
		super();
		this.roomNumber = roomNumber;
		this.room = room;
		this.price = price;
		this.hotelName = hotelName;
		this.typeOfBathRoom = typeOfBathRoom;
		this.typeOfRoom = typeOfRoom;
	}

	public RoomService(int roomNumber, int room, BigDecimal price) {
		super();
		this.roomNumber = roomNumber;
		this.room = room;
		this.price = price;
	}

	public RoomService(BigDecimal price) {
		super();
		this.price = price;
	}

	public RoomService() {
		super();
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public HotelName getHotelName() {
		return hotelName;
	}

	public void setHotelName(HotelName hotelName) {
		this.hotelName = hotelName;
	}

	public TypeOfBathRoom getTypeOfBathRoom() {
		return typeOfBathRoom;
	}

	public void setTypeOfBathRoom(TypeOfBathRoom typeOfBathRoom) {
		this.typeOfBathRoom = typeOfBathRoom;
	}

	public TypeOfRoom getTypeOfRoom() {
		return typeOfRoom;
	}

	public void setTypeOfRoom(TypeOfRoom typeOfRoom) {
		this.typeOfRoom = typeOfRoom;
	}

}
