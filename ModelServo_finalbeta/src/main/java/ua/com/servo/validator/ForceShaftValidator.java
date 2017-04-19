package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.entity.ForceShaft;
import ua.com.servo.service.ForceShaftService;



public class ForceShaftValidator implements Validator {

	private final ForceShaftService forceShaftService;
	
	public ForceShaftValidator(ForceShaftService forceShaftService) {
		this.forceShaftService = forceShaftService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ForceShaftForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ForceShaftForm form = (ForceShaftForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "forceShaft", "", "Can not be empty");
		if (forceShaftService.findByforceShaft(form.getForceShaft())!=null){
			errors.rejectValue("forceShaft", "", "Alrady exsist");
		}
	}

}
