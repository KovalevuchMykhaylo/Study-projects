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

import ua.com.servo.entity.Type;
import ua.com.servo.service.TypeService;
import ua.com.servo.validator.TypeValidator;


@Controller
@RequestMapping("/admin/type")
@SessionAttributes("type")
public class TypeController {

	@Autowired
	private TypeService typeService;
	
	@InitBinder("type")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new TypeValidator(typeService));
	}
	
	@ModelAttribute("type")
	public Type getForm(){
		return new Type();
	}
	
	@RequestMapping
	public String show(Model model){
		model.addAttribute("types", typeService.findAll());
		return "admin-type";
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/type";
	}
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable int id, Model model){
		model.addAttribute("type", typeService.findOne(id));
		return show(model);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable int id){
		typeService.delete(id);
		return "redirect:/admin/type";
	}
	
	@PostMapping
	public String save(@ModelAttribute("type") @Valid Type type, BindingResult br, Model model, SessionStatus status) {
		if(br.hasErrors()) return show(model);
		typeService.save(type);
		status.setComplete();
		return "redirect:/admin/type";
	}
}
