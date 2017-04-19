package ua.com.servo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.servo.entity.Size;
import ua.com.servo.service.SizeService;

public class SizeValidator implements Validator {

	private final SizeService sizeService;
	
	public SizeValidator(SizeService sizeService) {
		super();
		this.sizeService = sizeService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		
		return Size.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Size size = (Size) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "", "Can not be empty");
		if (sizeService.findBySize(size.getSize())!=null){
			errors.rejectValue("size", "", "Alrady exsist");
		}
	}

}
