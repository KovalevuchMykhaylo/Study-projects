package ua.com.hotel.controller.subuser;

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
import ua.com.hotel.entity.TypeOfRoom;
import ua.com.hotel.service.TypeOfRoomService;
import ua.com.hotel.validator.TypeOfRoomValidator;
import static ua.com.hotel.util.ParamBuilder.*;

@Controller
@RequestMapping("/subuser/typeOfSubRoom")
@SessionAttributes("typeOfRoom")
public class TypeOfRoomSubController {

	@Autowired
	private TypeOfRoomService typeOfRoomService;
	
	@InitBinder("typeOfRoom")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new TypeOfRoomValidator(typeOfRoomService));
	}
	
	@ModelAttribute("typeOfRoom")
	public TypeOfRoom getForm(){
	return new TypeOfRoom();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter(){
		return new SimpleFilter();
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/subuser/typeOfSubRoom";
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", typeOfRoomService.findAll(pageable, filter));
		return "subuser-typeOfSubRoom";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
	model.addAttribute("typeOfRoom", typeOfRoomService.findOne(id));
	return show(model, pageable, filter);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		typeOfRoomService.delete(id);
		return "redirect:/subuser/typeOfSubRoom"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute ("typeOfRoom") @Valid TypeOfRoom typeOfRoom, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		typeOfRoomService.save(typeOfRoom);
		status.setComplete();
		return "redirect:/subuser/typeOfSubRoom"+getParams(pageable, filter);
	}
}
