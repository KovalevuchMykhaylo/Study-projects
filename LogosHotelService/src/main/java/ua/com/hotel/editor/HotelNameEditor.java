package ua.com.hotel.editor;

import java.beans.PropertyEditorSupport;

import ua.com.hotel.entity.HotelName;
import ua.com.hotel.service.HotelNameService;

public class HotelNameEditor extends PropertyEditorSupport{
	
	private final HotelNameService hotelNameService;

	public HotelNameEditor(HotelNameService hotelNameService) {
		this.hotelNameService = hotelNameService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		HotelName hotelName = hotelNameService.findOne(Long.valueOf(text));
		setValue(hotelName);
	}
}
