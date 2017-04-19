package ua.com.servo.editor;

import java.beans.PropertyEditorSupport;

import ua.com.servo.entity.Weight;
import ua.com.servo.service.WeightService;

public class WeightEditor extends PropertyEditorSupport{
private final WeightService weightService;

public WeightEditor(WeightService weightService) {
	super();
	this.weightService = weightService;
}

@Override
public void setAsText(String text) throws IllegalArgumentException {
	Weight weight = weightService.findOne(Integer.valueOf(text));
	setValue(weight);
}

}
