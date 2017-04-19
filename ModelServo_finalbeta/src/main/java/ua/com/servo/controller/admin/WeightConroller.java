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
import ua.com.servo.service.WeightService;
import ua.com.servo.validator.WeightValidator;
import ua.com.servo.dto.form.WeightForm;

@Controller
@RequestMapping("/admin/weight")
@SessionAttributes("weight")
public class WeightConroller {

	@Autowired
	private WeightService weightService;

	@ModelAttribute("weight")
	public WeightForm getForm() {
		return new WeightForm();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}
	
	@InitBinder("weight")
	protected void bind (WebDataBinder binder){
		binder.setValidator(new WeightValidator(weightService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", weightService.findAll(pegeable, filter));
		return "admin-weight";
	}
	
	@GetMapping ("/update/{id}")
	public String update(@PathVariable Integer id, Model model,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("weight", weightService.findForm(id));
		return show (model, pegeable, filter);
	}

	@RequestMapping ("/delete/{id}")
	public String delete(@PathVariable Integer id,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		weightService.delete(id);
		return "redirect:/admin/weight"+getParams(pegeable, filter);
}
	@PostMapping
	public String save(@ModelAttribute ("weight") @Valid WeightForm weightForm, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pegeable, filter);
		weightService.save(weightForm);
		status.setComplete();
		return "redirect:/admin/weight"+getParams(pegeable, filter);
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/weight";
	}
}
