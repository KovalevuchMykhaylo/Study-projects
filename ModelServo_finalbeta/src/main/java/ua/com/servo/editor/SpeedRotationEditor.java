package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;
import ua.com.servo.entity.SpeedRotation;

import ua.com.servo.service.SpeedRotationService;

public class SpeedRotationEditor extends PropertyEditorSupport {
private final SpeedRotationService speedRotationService;

public SpeedRotationEditor(SpeedRotationService speedRotationService) {
	super();
	this.speedRotationService = speedRotationService;
}

@Override
public void setAsText(String text) throws IllegalArgumentException {
	SpeedRotation speedRotation = speedRotationService.findOne(Integer.valueOf(text));
	setValue(speedRotation);
}
}
