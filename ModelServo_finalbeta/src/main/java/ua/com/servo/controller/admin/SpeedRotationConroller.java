package ua.com.servo.controller.admin;

import static ua.com.servo.util.ParamBuilder.getParams;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import ua.com.servo.dto.filter.SimpleFilter;

import ua.com.servo.dto.form.SpeedRotationForm;

import ua.com.servo.service.SpeedRotationService;

import ua.com.servo.validator.SpeedRotationValidator;

@Controller
@RequestMapping("/admin/speedRotation")
@SessionAttributes("speedRotation")
public class SpeedRotationConroller {
	@Autowired
	private SpeedRotationService speedRotationService;

	@ModelAttribute("speedRotation")
	public SpeedRotationForm getForm() {
		return new SpeedRotationForm();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}
	
	@InitBinder("speedRotation")
	protected void bind (WebDataBinder binder){
		binder.setValidator(new SpeedRotationValidator(speedRotationService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", speedRotationService.findAll(pegeable, filter));
		return "admin-speedRotation";
	}
	
	@GetMapping ("/update/{id}")
	public String update(@PathVariable Integer id, Model model,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("speedRotation", speedRotationService.findForm(id));
		return show (model, pegeable, filter);
	}

	@RequestMapping ("/delete/{id}")
	public String delete(@PathVariable Integer id,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		speedRotationService.delete(id);
		return "redirect:/admin/speedRotation"+getParams(pegeable, filter);
}
	@PostMapping
	public String save(@ModelAttribute ("speedRotation") @Valid SpeedRotationForm speedRotationForm, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pegeable, filter);
		speedRotationService.save(speedRotationForm);
		status.setComplete();
		return "redirect:/admin/speedRotation"+getParams(pegeable, filter);
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/speedRotation";
	}
}


