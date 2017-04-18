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

import ua.com.hotel.entity.TypeOfBathRoom;
import ua.com.hotel.service.TypeOfBathRoomService;
import ua.com.hotel.validator.TypeOfBathRoomValidator;
import ua.com.hotel.dto.filter.SimpleFilter;
import static ua.com.hotel.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/typeOfBathRoom")
@SessionAttributes("typeOfBathRoom")
public class TypeOfBathRoomController {

	@Autowired
	private TypeOfBathRoomService typeOfBathRoomService;
	
	@InitBinder("typeOfBathRoom")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new TypeOfBathRoomValidator(typeOfBathRoomService));
	}
	
	@ModelAttribute("typeOfBathRoom")
	public TypeOfBathRoom getForm(){
		return new TypeOfBathRoom();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/typeOfBathRoom";
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", typeOfBathRoomService.findAll(pageable, filter));
		return "admin-typeOfBathRoom";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
	model.addAttribute("typeOfBathRoom", typeOfBathRoomService.findOne(id));
		return show(model, pageable, filter);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		typeOfBathRoomService.delete(id);
		return "redirect:/admin/typeOfBathRoom"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("typeOfBathRoom") @Valid TypeOfBathRoom typeOfBathRoom, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		typeOfBathRoomService.save(typeOfBathRoom);
		status.setComplete();
		return "redirect:/admin/typeOfBathRoom"+getParams(pageable, filter);
	}
}
