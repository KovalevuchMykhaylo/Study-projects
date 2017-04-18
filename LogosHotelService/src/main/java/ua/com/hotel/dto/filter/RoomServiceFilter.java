package ua.com.hotel.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RoomServiceFilter {
	
	private String max = "";
	
	private String min = "";
	
	private BigDecimal maxValue;
	
	private BigDecimal minValue;
	
	private List <Long> hotelNameId = new ArrayList<>();
	
	private List <Long> typeOfBathRoomId = new ArrayList<>();
	
	private List <Long> typeOfRoomId = new ArrayList<>();

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public List<Long> getHotelNameId() {
		return hotelNameId;
	}

	public void setHotelNameId(List<Long> hotelNameId) {
		this.hotelNameId = hotelNameId;
	}

	public List<Long> getTypeOfBathRoomId() {
		return typeOfBathRoomId;
	}

	public void setTypeOfBathRoomId(List<Long> typeOfBathRoomId) {
		this.typeOfBathRoomId = typeOfBathRoomId;
	}

	public List<Long> getTypeOfRoomId() {
		return typeOfRoomId;
	}

	public void setTypeOfRoomId(List<Long> typeOfRoomId) {
		this.typeOfRoomId = typeOfRoomId;
	}
	
}
