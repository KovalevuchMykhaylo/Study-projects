package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.dto.form.ModelServoForm;
import ua.com.servo.entity.ForceShaft;
import ua.com.servo.entity.ModelServo;
import ua.com.servo.service.ForceShaftService;
import ua.com.servo.service.ModelServoService;



public class ModelServoValitator implements Validator {


	private final ModelServoService  modelServoService;
	
	

	public ModelServoValitator(ModelServoService modelServoService) {
		super();
		this.modelServoService = modelServoService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return ModelServoForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ModelServoForm form = (ModelServoForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can not be empty");
		if (modelServoService.findByName(form.getName())!=null){
			errors.rejectValue("name", "", "Alrady exsist");
		}
	}

}
