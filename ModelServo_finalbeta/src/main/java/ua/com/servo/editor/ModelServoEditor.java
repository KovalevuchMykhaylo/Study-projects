package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.ModelServo;
import ua.com.servo.service.ModelServoService;

public class ModelServoEditor extends PropertyEditorSupport {
	private final ModelServoService modelServoService;

	public ModelServoEditor(ModelServoService modelServoService) {
		this.modelServoService = modelServoService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ModelServo modelServo = modelServoService.findOne(Integer.valueOf(text));
		setValue(modelServo);

	}

}
