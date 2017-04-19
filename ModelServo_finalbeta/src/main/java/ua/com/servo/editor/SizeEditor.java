package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.Size;
import ua.com.servo.service.SizeService;

public class SizeEditor extends PropertyEditorSupport{
	private final SizeService sizeService;

	public SizeEditor(SizeService sizeService) {
		super();
		this.sizeService = sizeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Size size = sizeService.findOne(Integer.valueOf(text));
		setValue(size);
	}

 
}
