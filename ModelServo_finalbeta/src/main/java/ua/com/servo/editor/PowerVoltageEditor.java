package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.PowerVoltage;
import ua.com.servo.service.PowerVoltageService;

public class PowerVoltageEditor extends PropertyEditorSupport{
	private final PowerVoltageService powerVoltageService;

	public PowerVoltageEditor(PowerVoltageService powerVoltageService) {
		this.powerVoltageService = powerVoltageService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		 PowerVoltage powerVoltage = powerVoltageService.findOne(Integer.valueOf(text));
		setValue(powerVoltage);
	}

 
}
