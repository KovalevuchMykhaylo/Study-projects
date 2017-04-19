package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.entity.PowerVoltage;
import ua.com.servo.service.PowerVoltageService;

public class PowerVoltageValidator  implements Validator{
private final PowerVoltageService powerVoltageService;

public PowerVoltageValidator(PowerVoltageService powerVoltageService) {
	this.powerVoltageService = powerVoltageService;
}

@Override
public boolean supports(Class<?> clazz) {
	return PowerVoltage.class.equals(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	PowerVoltage powerVoltage = (PowerVoltage) target;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "powerVoltage", "", "Can not be empty");
	if (powerVoltageService.findByPowerVoltage(powerVoltage.getPowerVoltage())!=null){
		errors.rejectValue("powerVoltage", "", "Alrady exsist");
	}
}
	
}  

