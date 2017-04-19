package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import ua.com.servo.dto.form.SpeedRotationForm;

import ua.com.servo.service.SpeedRotationService;

public class SpeedRotationValidator implements Validator {

	private final SpeedRotationService speedRotationService;
	
	public SpeedRotationValidator(SpeedRotationService speedRotationService) {
		super();
		this.speedRotationService = speedRotationService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return SpeedRotationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		SpeedRotationForm form = (SpeedRotationForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "speedRotation", "", "Can not be empty");
		if (speedRotationService.findByspeedRotation(form.getSpeedRotation())!=null){
			errors.rejectValue("speedrotation", "", "Alrady exsist");
		}
	}
}
