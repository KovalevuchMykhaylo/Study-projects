package ua.com.hotel.dto.filter;

import java.util.ArrayList;
import java.util.List;

public class HotelNameFilter {
	
	private String name;
	
	private String adress;
	
	private List <Long> cityId = new ArrayList<>();
	
	private List <Long> userId = new ArrayList<>();

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Long> getCityId() {
		return cityId;
	}

	public void setCityId(List<Long> cityId) {
		this.cityId = cityId;
	}

	public List<Long> getUserId() {
		return userId;
	}

	public void setUserId(List<Long> userId) {
		this.userId = userId;
	}
	
	
}
