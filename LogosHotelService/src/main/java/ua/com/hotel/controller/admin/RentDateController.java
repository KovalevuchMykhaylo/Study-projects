package ua.com.hotel.controller.admin;

import static ua.com.hotel.util.ParamBuilder.getParams;

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

import ua.com.hotel.dto.form.RentDateForm;
import ua.com.hotel.editor.HotelNameEditor;
import ua.com.hotel.editor.RoomServiceEditor;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.RoomService;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.service.RentDateService;
import ua.com.hotel.service.RoomServiceService;
import ua.com.hotel.validator.RentDateValidator;

@Controller
@RequestMapping("/admin/rentDate")
@SessionAttributes("rentDate")
public class RentDateController {

	@Autowired
	private RentDateService rentDateService;
	
	@Autowired
	private RoomServiceService roomServiceService;
	
	@Autowired
	private HotelNameService hotelNameService;
	
	@ModelAttribute("rentDate")
	public RentDateForm getForm(){
		return new RentDateForm();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable){
		model.addAttribute("page", rentDateService.findAll(pageable));
		model.addAttribute("roomService", roomServiceService.findAll());
//		model.addAttribute("hotelName", hotelNameService.findHotelNameByRentDate());
		return "admin-rentDate";
	}
	@InitBinder("rentDate")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new RentDateValidator(rentDateService));
		binder.registerCustomEditor(RoomService.class, new RoomServiceEditor(roomServiceService));
		binder.registerCustomEditor(HotelName.class, new HotelNameEditor(hotelNameService));
	}
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable Long id, Model model, @PageableDefault Pageable pageable){
		model.addAttribute("rentDate", rentDateService.finForm(id));
		return show(model, pageable);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable){
		rentDateService.delete(id);
		return "redirect:/admin/rentDate"+getParams(pageable);
	}
	@PostMapping
	public String save(@ModelAttribute ("rentDate") @Valid RentDateForm rentDateForm, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable){
		if(br.hasErrors()) return show(model, pageable);
		rentDateService.save(rentDateForm);
		status.setComplete();
		return "redirect:/admin/rentDate"+getParams(pageable);
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/rentDate";
	}
}
