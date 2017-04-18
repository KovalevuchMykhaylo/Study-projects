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

import ua.com.hotel.dto.filter.HotelNameFilter;
import ua.com.hotel.editor.CityEditor;
import ua.com.hotel.editor.UserEditor;
import ua.com.hotel.entity.City;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.User;
import ua.com.hotel.service.CityService;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.service.UserService;
import ua.com.hotel.validator.HotelNameValidator;
import ua.com.hotel.util.ParamBuilder;

@Controller
@RequestMapping("/admin/hotelName")
@SessionAttributes("hotelName")
public class HotelNameController {

	@Autowired
	private HotelNameService hotelNameService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private UserService userService;
	
	@InitBinder("hotelName")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(City.class, new CityEditor(cityService));
		binder.registerCustomEditor(User.class, new UserEditor(userService));
		binder.setValidator(new HotelNameValidator(hotelNameService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") HotelNameFilter filter) {
		model.addAttribute("page", hotelNameService.findAll(pageable, filter));
		model.addAttribute("citys", cityService.findAll());
		model.addAttribute("users", userService.findAll());
		return "admin-hotelName";
	}
	
	@ModelAttribute("hotelName")
	public HotelName getForm(){
		return new HotelName();
	}
	
	@ModelAttribute("filter")
	public HotelNameFilter getFilter(){
		return new HotelNameFilter();
	}

	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") HotelNameFilter filter) {
		hotelNameService.delete(id);
		return "redirect:/admin/hotelName"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") HotelNameFilter filter){
		model.addAttribute("hotelName", hotelNameService.findOne(id));
		return show(model, pageable, filter);
	}

	@PostMapping
	public String save(@ModelAttribute ("hotelName") @Valid HotelName hotelName, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") HotelNameFilter filter) {
		if(br.hasErrors()) return show(model, pageable, filter);
		hotelNameService.save(hotelName);
		status.setComplete();
		return "redirect:/admin/hotelName"+ getParams(pageable, filter);
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/hotelName";
	}
	
	private String getParams(Pageable pageable, HotelNameFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder builder = new StringBuilder(page);
		if(!filter.getName().isEmpty()){
			builder.append("&name=");
			builder.append(filter.getName());
		}
		if(!filter.getCityId().isEmpty()){
			for (Long id : filter.getCityId()) {
				builder.append("&cityId=");
				builder.append(id);
			}
		}
		
	if(!filter.getUserId().isEmpty()){
		for (Long id : filter.getUserId()) {
			builder.append("&userId=");
			builder.append(id);
		}
	}
	return builder.toString();
}
}
