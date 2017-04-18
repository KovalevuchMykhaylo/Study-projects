package ua.com.hotel.dto.form;

import ua.com.hotel.entity.RoomService;
import ua.com.hotel.entity.User;

public class RentDateForm {
	
	private Long id;
	
	private String first;
	
	private String second;
	
	private User user;
	
	private RoomService roomService;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	
	

}
