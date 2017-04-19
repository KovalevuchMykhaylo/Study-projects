package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.dto.form.RotationAngleForm;
import ua.com.servo.entity.ForceShaft;
import ua.com.servo.service.ForceShaftService;
import ua.com.servo.service.RotationAngleService;



public class RotationAngleValidator implements Validator {

	private final RotationAngleService rotationAngleService;
	
	public RotationAngleValidator(RotationAngleService rotationAngleService) {
		this.rotationAngleService = rotationAngleService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ForceShaftForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		RotationAngleForm form = (RotationAngleForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "angle", "", "Can not be empty");
		if (rotationAngleService.findByAngle(form.getAngle())!=null){
			errors.rejectValue("angle", "", "Alrady exsist");
		}
	}

}
