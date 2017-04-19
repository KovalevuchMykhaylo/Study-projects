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

import ua.com.servo.entity.GearType;
import ua.com.servo.service.GearTypeService;
import ua.com.servo.validator.GearTypeValidator;

@Controller
@RequestMapping("/admin/gearType")
@SessionAttributes("gearType")
public class GearTypeController {

	@Autowired
	private GearTypeService gearTypeService;
	
	@InitBinder("gearType")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new GearTypeValidator(gearTypeService));
	}
	
	@ModelAttribute("gearType")
	public GearType getForm(){
		return new GearType();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("gearTypes", gearTypeService.findAll());
		return "admin-gearType";
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/gearType";
	}
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable int id, Model model){
		model.addAttribute("gearType", gearTypeService.findOne(id));
		return show(model);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		gearTypeService.delete(id);
		return "redirect:/admin/gearType";
	}
	
	@PostMapping
	public String save(@ModelAttribute("gearType") @Valid GearType gearType, BindingResult br, Model model, SessionStatus status) {
		if(br.hasErrors()) return show(model);
		gearTypeService.save(gearType);
		status.setComplete();
		return "redirect:/admin/gearType";
	}
}
