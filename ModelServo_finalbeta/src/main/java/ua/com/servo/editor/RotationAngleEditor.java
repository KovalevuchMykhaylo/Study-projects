package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.RotationAngle;
import ua.com.servo.service.RotationAngleService;

public class RotationAngleEditor extends PropertyEditorSupport {
private final RotationAngleService rotationAngleService;

public RotationAngleEditor(RotationAngleService rotationAngleService) {
	super();
	this.rotationAngleService = rotationAngleService;
}

@Override
public void setAsText(String text) throws IllegalArgumentException {
RotationAngle rotationAngle = rotationAngleService.findOne(Integer.valueOf(text));
setValue(rotationAngle);
}

}
