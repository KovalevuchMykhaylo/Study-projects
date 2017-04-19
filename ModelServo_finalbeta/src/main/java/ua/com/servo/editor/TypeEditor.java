package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.Type;
import ua.com.servo.service.TypeService;

public class TypeEditor extends PropertyEditorSupport {
	private final TypeService typeService;

	

	public TypeEditor(TypeService typeService) {
		super();
		this.typeService = typeService;
	}



	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Type type =  typeService.findOne(Integer.valueOf(text));
		setValue(type);

	}

}
