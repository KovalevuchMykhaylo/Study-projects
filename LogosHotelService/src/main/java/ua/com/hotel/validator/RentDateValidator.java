package ua.com.hotel.validator;

import java.time.LocalDate;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.hotel.dto.form.RentDateForm;
import ua.com.hotel.service.RentDateService;

public class RentDateValidator implements Validator{
	
	private final static Pattern REG = Pattern.compile("^(20[0-9][0-9]|2100)\\.([0][1-9]|[1][012])\\.([012][1-9]|[3][01])$");
	
	private final RentDateService rentDateService;

	public RentDateValidator(RentDateService rentDateService) {
		this.rentDateService = rentDateService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return RentDateForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		Перевірка заповнення полів
		RentDateForm form = (RentDateForm) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first", "", "Can't be empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "second", "", "Can't be empty");
		if(!REG.matcher(form.getFirst()).matches() && !REG.matcher(form.getSecond()).matches()){
			errors.rejectValue("first", "", "Can be separated only . or write only numbers");
			errors.rejectValue("second", "", "Can be separated only . or write only numbers");
		}
//		Перевірка дат оренди
		System.out.println(rentDateService.findDateByRoomService(LocalDate.parse(form.getFirst().replace(".", "-")), LocalDate.parse(form.getSecond().replace(".", "-")), form.getRoomService().getId()));
		if(!rentDateService.findDateByRoomService(LocalDate.parse(form.getFirst().replace(".", "-")), LocalDate.parse(form.getSecond().replace(".", "-")), form.getRoomService().getId()).isEmpty()){
			errors.rejectValue("first", "", "Allready exists");
			errors.rejectValue("second", "", "Allready exists");
			}
	}
}
	
