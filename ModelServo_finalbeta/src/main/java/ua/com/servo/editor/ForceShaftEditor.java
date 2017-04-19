package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.ForceShaft;
import ua.com.servo.service.ForceShaftService;

public class ForceShaftEditor extends PropertyEditorSupport {
	private final ForceShaftService forceShaftService;

	public ForceShaftEditor(ForceShaftService forceShaftService) {
		super();
		this.forceShaftService = forceShaftService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ForceShaft forceShaft = forceShaftService.findOne(Integer.valueOf(text));
		setValue(forceShaft);

	}

}
