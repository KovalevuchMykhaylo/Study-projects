package ua.com.hotel.controller.admin;

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

import ua.com.hotel.dto.filter.SimpleFilter;
import ua.com.hotel.entity.City;
import ua.com.hotel.service.CityService;
import ua.com.hotel.validator.CityValidator;
import static ua.com.hotel.util.ParamBuilder.*;


@Controller
@RequestMapping("/admin/city")
@SessionAttributes("city")
public class CityController {

	@Autowired
	private CityService cityService;
	
	@InitBinder("city")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new CityValidator(cityService));		
	}
	
	@ModelAttribute("city")
	public City getForm(){
		return new City();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", cityService.findAll(pageable, filter));
		return "admin-city";
	}
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("city", cityService.findOne(id));
		return show(model, pageable, filter);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		cityService.delete(id);
		return "redirect:/admin/city"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute ("city") @Valid City city, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		cityService.save(city);
		status.setComplete();
		return "redirect:/admin/city"+getParams(pageable, filter);
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/city";
	}
}

