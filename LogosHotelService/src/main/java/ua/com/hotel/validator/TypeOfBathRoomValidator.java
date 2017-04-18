package ua.com.hotel.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.hotel.entity.TypeOfBathRoom;
import ua.com.hotel.service.TypeOfBathRoomService;

public class TypeOfBathRoomValidator implements Validator{
	
	private final TypeOfBathRoomService typeOfBathRoomService;

	public TypeOfBathRoomValidator(TypeOfBathRoomService typeOfBathRoomService) {
		this.typeOfBathRoomService = typeOfBathRoomService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TypeOfBathRoom.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TypeOfBathRoom typeOfBathRoom = (TypeOfBathRoom) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "type", "", "Can't be empty");
		if(typeOfBathRoomService.findByType(typeOfBathRoom.getType())!=null){
			errors.rejectValue("type", "", "Already exist");
		}
	}

}
