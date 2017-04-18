package ua.com.hotel.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.hotel.entity.HotelName;
import ua.com.hotel.service.HotelNameService;

public class HotelNameValidator implements Validator {
	
	private final static Pattern REG = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	
	private final HotelNameService hotelNameService;

	public HotelNameValidator(HotelNameService hotelNameService) {
		this.hotelNameService = hotelNameService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return HotelName.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		HotelName hotelName = (HotelName) target;
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adress", "", "Can't be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "", "Can't be empty and containes only numbers");
			if(!REG.matcher(hotelName.geteMail()).matches()) {
				errors.rejectValue("eMail", "", "This is not email");
			if (hotelNameService.findUnique(hotelName.getName(), hotelName.getCity(), hotelName.getUser()) != null) {
				errors.rejectValue("name", "", "Already exist");
			}
		}
	}
}
