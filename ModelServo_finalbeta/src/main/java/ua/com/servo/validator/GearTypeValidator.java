package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.entity.GearType;
import ua.com.servo.entity.Type;
import ua.com.servo.service.GearTypeService;
import ua.com.servo.service.TypeService;

public class GearTypeValidator  implements Validator{
private final GearTypeService gearTypeService;

public GearTypeValidator(GearTypeService gearTypeService) {
	this.gearTypeService = gearTypeService;
}

@Override
public boolean supports(Class<?> clazz) {
	return GearType.class.equals(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	GearType gearType = (GearType) target;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gearType", "", "Can not be empty");
	if (gearTypeService.findByType(gearType.getGearType())!=null){
		errors.rejectValue("gearType", "", "Alrady exsist");
	}
}
	
}  

