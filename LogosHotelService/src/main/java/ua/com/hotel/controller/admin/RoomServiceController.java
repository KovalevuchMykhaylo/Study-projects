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

import ua.com.hotel.dto.filter.RoomServiceFilter;
import ua.com.hotel.dto.form.RoomServiceForm;
import ua.com.hotel.editor.HotelNameEditor;
import ua.com.hotel.editor.TypeOfBathRoomEditor;
import ua.com.hotel.editor.TypeOfRoomEditor;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.TypeOfBathRoom;
import ua.com.hotel.entity.TypeOfRoom;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.service.RoomServiceService;
import ua.com.hotel.service.TypeOfBathRoomService;
import ua.com.hotel.service.TypeOfRoomService;
import ua.com.hotel.util.ParamBuilder;
import ua.com.hotel.validator.RoomServiceValidator;

@Controller
@RequestMapping("/admin/roomService")
@SessionAttributes("roomService")
public class RoomServiceController {

	@Autowired
	private RoomServiceService roomServiceService;
	
	@Autowired
	private HotelNameService hotelNameService;
	
	@Autowired
	private TypeOfBathRoomService typeOfBathRoomService;
	
	@Autowired
	private TypeOfRoomService typeOfRoomService;
	
	@InitBinder("roomService")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(HotelName.class, new HotelNameEditor(hotelNameService));
		binder.registerCustomEditor(TypeOfRoom.class, new TypeOfRoomEditor(typeOfRoomService));
		binder.registerCustomEditor(TypeOfBathRoom.class, new TypeOfBathRoomEditor(typeOfBathRoomService));
		binder.setValidator(new RoomServiceValidator(roomServiceService));
	}
	
	@ModelAttribute("filter")
	public RoomServiceFilter getFilter(){
		return new RoomServiceFilter();
	}
	
	@ModelAttribute("roomService")
	public RoomServiceForm getForm(){
		return new RoomServiceForm();
	}
	
	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") RoomServiceFilter filter){
		model.addAttribute("page", roomServiceService.findAll(pageable, filter));
		model.addAttribute("roomServices", roomServiceService.findAll());
		model.addAttribute("hotelNames", hotelNameService.findAll());
		model.addAttribute("typeOfRooms", typeOfRoomService.findAll());
		model.addAttribute("typeOfBathRooms", typeOfBathRoomService.findAll());
		return "admin-roomService";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") RoomServiceFilter filter){
		roomServiceService.delete(id);
		return "redirect:/admin/roomService"+ getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") RoomServiceFilter filter){
		model.addAttribute("roomService", roomServiceService.finForm(id));
		return show(model, pageable, filter);
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/roomService";
	}
	
	@PostMapping
	public String save(@ModelAttribute ("roomService") @Valid RoomServiceForm roomServiceForm, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") RoomServiceFilter filter) {
		if(br.hasErrors())return show(model, pageable, filter);
		roomServiceService.save(roomServiceForm);
		status.setComplete();
		return "redirect:/admin/roomService"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, RoomServiceFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder builder = new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			builder.append("&max=");
			builder.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			builder.append("&min=");
			builder.append(filter.getMin());
		}
		if(!filter.getHotelNameId().isEmpty()){
			for (Long id : filter.getHotelNameId()) {
				builder.append("&hotelNameId=");
				builder.append(id);
			}
		}
		if(!filter.getTypeOfRoomId().isEmpty()){
			for (Long id : filter.getTypeOfRoomId()) {
				builder.append("&typeOfRoomId=");
				builder.append(id);
			}
		}
		if(!filter.getTypeOfBathRoomId().isEmpty()){
			for (Long id : filter.getTypeOfBathRoomId()) {
				builder.append("&typeOfBathRoomId=");
				builder.append(id);
			}
		}
		return builder.toString();
	}
}
