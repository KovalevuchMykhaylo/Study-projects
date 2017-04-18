package ua.com.hotel.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//Дата оренди
@Entity
@Table(name = "rent", indexes = @Index(columnList = "first, second"))
public class RentDate extends AbstractEntity {
	@Column
	private LocalDate first;
	@Column
	private LocalDate second;
	@ManyToOne(fetch = FetchType.LAZY)
	private RoomService roomService;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	public RentDate() {
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

	public LocalDate getFirst() {
		return first;
	}

	public void setFirst(LocalDate first) {
		this.first = first;
	}

	public LocalDate getSecond() {
		return second;
	}

	public void setSecond(LocalDate second) {
		this.second = second;
	}

	@Override
	public String toString() {
		return "RentDate [first=" + first + ", second=" + second + "]";
	}

}
