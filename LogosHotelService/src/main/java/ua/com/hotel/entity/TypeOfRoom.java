package ua.com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Тип номера (первий клас, президенський номер, люкс...)
@Entity
@Table(name = "type_of_room", indexes = @Index(columnList = "type"))
public class TypeOfRoom extends AbstractEntity{
	@Column(name = "type")
	private String type;
	@OneToMany(mappedBy = "typeOfRoom")
	private List <RoomService> roomServices = new ArrayList<>();
	public TypeOfRoom(String type, List<RoomService> roomServices) {
		super();
		this.type = type;
		this.roomServices = roomServices;
	}
	public TypeOfRoom(String type) {
		super();
		this.type = type;
	}
	
	public TypeOfRoom() {
		super();
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<RoomService> getRoomServices() {
		return roomServices;
	}
	public void setRoomServices(List<RoomService> roomServices) {
		this.roomServices = roomServices;
	}
	
}