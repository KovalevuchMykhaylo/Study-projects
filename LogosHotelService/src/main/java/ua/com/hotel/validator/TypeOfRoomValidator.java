package ua.com.hotel.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.hotel.entity.TypeOfRoom;
import ua.com.hotel.service.TypeOfRoomService;

public class TypeOfRoomValidator implements Validator{
	
	private final TypeOfRoomService typeOfRoomService;
	
	public TypeOfRoomValidator(TypeOfRoomService typeOfRoomService) {
		this.typeOfRoomService = typeOfRoomService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TypeOfRoom.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TypeOfRoom typeOfRoom = (TypeOfRoom) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "", "Can't be empty");
		if(typeOfRoomService.findByType(typeOfRoom.getType())!=null){
			errors.rejectValue("type", "", "Already exist");
		}
	}
}
