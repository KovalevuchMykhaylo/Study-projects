package ua.com.hotel.editor;

import java.beans.PropertyEditorSupport;

import ua.com.hotel.entity.City;
import ua.com.hotel.service.CityService;

public class CityEditor extends PropertyEditorSupport{
	
	private final CityService cityService;

	public CityEditor(CityService cityService) {
		this.cityService = cityService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		City city = cityService.findOne(Long.valueOf(text));
		setValue(city);
	}
	
	
	

}
