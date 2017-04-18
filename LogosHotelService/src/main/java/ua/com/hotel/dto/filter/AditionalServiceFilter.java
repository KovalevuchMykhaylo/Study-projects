package ua.com.hotel.dto.filter;

import java.util.ArrayList;
import java.util.List;
//Фільтр для користувача
public class AditionalServiceFilter {
	
	private String type;
	
	private List <Long> hotelNameId = new ArrayList<>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Long> getHotelNameId() {
		return hotelNameId;
	}

	public void setHotelNameId(List<Long> hotelNameId) {
		this.hotelNameId = hotelNameId;
	}


}
