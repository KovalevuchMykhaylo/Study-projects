package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.dto.form.WeightForm;
import ua.com.servo.service.WeightService;

public class WeightValidator  implements Validator{
private final WeightService weightService;


public WeightValidator(WeightService weightService) {
	super();
	this.weightService = weightService;
}

@Override
public boolean supports(Class<?> clazz) {
	// TODO Auto-generated method stub
	return WeightForm.class.equals(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	WeightForm form = (WeightForm) target;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "weight", "", "Can not be empty");
	if (weightService.findByweight(form.getWeight())!=null){
		errors.rejectValue("weight", "", "Alrady exsist");
	}
}
	
}  

