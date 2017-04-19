package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.GearType;
import ua.com.servo.service.GearTypeService;

public class GearTypeEditor extends PropertyEditorSupport {
	private final GearTypeService gearTypeService;

	public GearTypeEditor(GearTypeService gearTypeService) {
		this.gearTypeService = gearTypeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		GearType gearType =  gearTypeService.findOne(Integer.valueOf(text));
		setValue(gearType);

	}

}
