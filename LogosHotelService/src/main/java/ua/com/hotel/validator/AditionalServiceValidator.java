package ua.com.hotel.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.hotel.entity.AditionalService;
import ua.com.hotel.service.AditionalServiceService;

public class AditionalServiceValidator implements Validator{
	
	private final AditionalServiceService aditionalServiceService;

	public AditionalServiceValidator(
			AditionalServiceService aditionalServiceService) {
		this.aditionalServiceService = aditionalServiceService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return AditionalService.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		AditionalService AditionalService = (AditionalService) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "", "Can't be empty");
		if(aditionalServiceService.findUnique(AditionalService.getType(), AditionalService.getHotelName())!=null){
			errors.rejectValue("type", "", "Already exist");
		}
	}
}
