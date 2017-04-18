package ua.com.hotel.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import ua.com.hotel.entity.City;
import ua.com.hotel.service.CityService;

public class CityValidator implements Validator{
	
	private final CityService cityService;

	public CityValidator(CityService cityService) {
		this.cityService = cityService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return City.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		City city = (City) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
		if(cityService.findByName(city.getName())!=null){
			errors.rejectValue("name", "", "Already exist");
		}
	}
}
