package ua.com.servo.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.servo.entity.PowerVoltage;
import ua.com.servo.service.PowerVoltageService;
import ua.com.servo.validator.PowerVoltageValidator;

@Controller
@RequestMapping("/admin/powerVoltage")
@SessionAttributes("powerVoltage")
public class PowerVoltageController {

	@Autowired
	private PowerVoltageService powerVoltageService;
	
	@InitBinder("powerVoltage")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new PowerVoltageValidator(powerVoltageService));
	}
	
	@ModelAttribute("powerVoltage")
	public PowerVoltage getForm(){
		return new PowerVoltage();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("powerVoltages", powerVoltageService.findAll());
		return "admin-powerVoltage";
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/powerVoltage";
	}
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable int id, Model model){
		model.addAttribute("powerVoltage", powerVoltageService.findOne(id));
		return show(model);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		powerVoltageService.delete(id);
		return "redirect:/admin/powerVoltage";
	}
	
	@PostMapping
	public String save(@ModelAttribute("powerVoltage") @Valid PowerVoltage powerVoltage, BindingResult br, Model model, SessionStatus status) {
		if(br.hasErrors()) return show(model);
		powerVoltageService.save(powerVoltage);
		status.setComplete();
		return "redirect:/admin/powerVoltage";
	}
}
