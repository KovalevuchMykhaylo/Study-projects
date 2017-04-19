package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.entity.Type;
import ua.com.servo.service.TypeService;

public class TypeValidator  implements Validator{
private final TypeService typeService;

public TypeValidator(TypeService typeService) {
	this.typeService = typeService;
}

@Override
public boolean supports(Class<?> clazz) {
	return Type.class.equals(clazz);
}

@Override
public void validate(Object target, Errors errors) {
	Type type = (Type) target;
	ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "", "Can not be empty");
	if (typeService.findByType(type.getType())!=null){
		errors.rejectValue("type", "", "Alrady exsist");
	}
}
	
}  

