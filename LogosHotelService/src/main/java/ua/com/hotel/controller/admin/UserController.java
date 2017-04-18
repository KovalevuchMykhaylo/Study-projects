package ua.com.hotel.controller.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.hotel.dto.filter.UserFilter;
import ua.com.hotel.entity.User;
import ua.com.hotel.service.CityService;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.service.RentDateService;
import ua.com.hotel.service.UserService;
import ua.com.hotel.util.ParamBuilder;

@Controller
@RequestMapping("/admin/user")
@SessionAttributes("user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private CityService cityService;

	@Autowired
	private HotelNameService hotelNameService;
	
	@Autowired
	private RentDateService rentDateService;

	@ModelAttribute("user")
	public User getForm() {
		return new User();
	}
	
	@ModelAttribute("filter")
	public UserFilter getFilter() {
		return new UserFilter();
	}

	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") UserFilter filter) {
		model.addAttribute("page", userService.findAll(pageable, filter));
		model.addAttribute("hotelNames", hotelNameService.findAllByUser());
		return "admin-user";
	}

	@RequestMapping("delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") UserFilter filter) {
		userService.delete(id);
		return "redirect:/admin/user"+ getParams(pageable, filter);
	}

	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") UserFilter filter) {
		model.addAttribute("user", userService.findOne(id));
		return show(model, pageable, filter);
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/user";
	}
		
		private String getParams(Pageable pageable, UserFilter filter){
			String page = ParamBuilder.getParams(pageable);
			StringBuilder builder = new StringBuilder(page);
			if(!filter.getHotelNamesId().isEmpty()){
				for (Long id : filter.getHotelNamesId()) {
					builder.append("&hotelNamesId=");
					builder.append(id);
				}
			}
			return builder.toString();
		}
	}
