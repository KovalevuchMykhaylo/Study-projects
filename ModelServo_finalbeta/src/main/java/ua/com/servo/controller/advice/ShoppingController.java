package ua.com.servo.controller.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;

import ua.com.servo.dto.Quantity;
import ua.com.servo.service.ModelServoService;

@ControllerAdvice
public class ShoppingController {
	
	@Autowired
	private ModelServoService modelServoService;
	
	@ModelAttribute("quantity")
	public Quantity getQuantity(@CookieValue(defaultValue="0", name="userId") int userId){
		int count = modelServoService.findCount(userId);
		return new Quantity(count);
	}
}