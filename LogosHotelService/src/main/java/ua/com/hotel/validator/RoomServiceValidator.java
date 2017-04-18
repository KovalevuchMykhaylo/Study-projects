package ua.com.hotel.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.hotel.dto.form.RoomServiceForm;
import ua.com.hotel.service.RoomServiceService;

public class RoomServiceValidator implements Validator{
	//Рег для бігдецімал
	private final static Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	//Рег приймаэ тільки інт
	private final static Pattern REG1 = Pattern.compile("^([0-9])$");
	
	private final RoomServiceService roomServiceService;
	
	public RoomServiceValidator(RoomServiceService roomServiceService) {
		this.roomServiceService = roomServiceService;
	}
//Об'єкт валідації
	@Override
	public boolean supports(Class<?> clazz) {
		return RoomServiceForm.class.equals(clazz);
	}
//Валідація
	@Override
	public void validate(Object target, Errors errors) {
		RoomServiceForm form = (RoomServiceForm) target;
		if(!REG.matcher(form.getPrice()).matches()){
			errors.rejectValue("price", "", "Can be separated only . or , or write only numbers");
		}
		if(!REG1.matcher(form.getRoom()).matches()){
			errors.rejectValue("room", "", "Write only numbers");
		}
		if(!REG1.matcher(form.getRoomNumber()).matches() ){
			errors.rejectValue("roomNumber", "", "Write only numbers");
		}
		if(errors.getFieldError("price")==null & (errors.getFieldError("room")==null) & (errors.getFieldError("roomNumber")==null)){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "", "Can't be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "room", "", "Can't be empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roomNumber", "", "Can't be empty");
			if(roomServiceService.findUnique(form.getPrice(), form.getRoom(), form.getRoomNumber(), form.getHotelName(), form.getTypeOfBathRoom(), form.getTypeOfRoom())!=null){//Перевірка номера на унікальність
				errors.rejectValue("price", "", "Write only numbers");
				errors.rejectValue("room", "", "Write only numbers");
				errors.rejectValue("roomNumber", "", "Room in this hotel already exist");
			}
		}
	}

}
