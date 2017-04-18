package ua.com.hotel.editor;

import java.beans.PropertyEditorSupport;

import ua.com.hotel.entity.RentDate;
import ua.com.hotel.service.RentDateService;

public class RentDateEditor extends PropertyEditorSupport{
	
	private final RentDateService rentDateService;

	public RentDateEditor(RentDateService rentDateService) {
		this.rentDateService = rentDateService;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		RentDate RentDate = rentDateService.findOne(Long.valueOf(text));
		setValue(RentDate);
	}
	

}
