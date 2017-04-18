package ua.com.hotel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
//Тип ванної кімнати (душ, ванна, сумісна...)
@Entity
@Table(name = "type_of_bathroom", indexes = @Index(columnList = "type"))
public class TypeOfBathRoom extends AbstractEntity{
	@Column(name = "type")
	private String type;
	@OneToMany(mappedBy = "typeOfBathRoom")
	private List<RoomService> roomServices = new ArrayList<>();
	public TypeOfBathRoom(String type, List<RoomService> roomServices) {
		super();
		this.type = type;
		this.roomServices = roomServices;
	}
	public TypeOfBathRoom(String type) {
		super();
		this.type = type;
	}
	public TypeOfBathRoom() {
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
