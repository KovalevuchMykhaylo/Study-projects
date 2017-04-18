package ua.com.hotel.controller.subuser;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

import ua.com.hotel.dto.filter.AditionalServiceFilter;
import ua.com.hotel.editor.HotelNameEditor;
import ua.com.hotel.entity.AditionalService;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.User;
import ua.com.hotel.service.AditionalServiceService;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.validator.AditionalServiceValidator;
import ua.com.hotel.util.ParamBuilder;

@Controller
@RequestMapping("/subuser/aditionalSubService")
@SessionAttributes("aditionalService")
public class AditionalServiceSubController {

	@Autowired
	private AditionalServiceService aditionalServiceService;
	
	@Autowired
	private HotelNameService hotelNameService;
	
	@InitBinder("aditionalService")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(HotelName.class, new HotelNameEditor(hotelNameService));
		binder.setValidator(new AditionalServiceValidator(aditionalServiceService));
	}
	
	@ModelAttribute("aditionalService")
	public AditionalService getForm(){
		return new AditionalService();
	}
	
	@ModelAttribute("filter")
	public AditionalServiceFilter getFilter(){
		return new AditionalServiceFilter();
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") AditionalServiceFilter filter){
		model.addAttribute("page", aditionalServiceService.findAll(pageable, filter));
		model.addAttribute("hotelNames", hotelNameService.findAllBySubUser(getSignedUpUser()));
		return "subuser-aditionalSubService";
	}
	
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/subuser/aditionalSubService";
	}
	
	@GetMapping("/update/{id}")
	public String update (@PathVariable Long id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") AditionalServiceFilter filter){
		model.addAttribute("aditionalService", aditionalServiceService.findOne(id));
		return show(model, pageable, filter);
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Long id, @PageableDefault Pageable pageable, @ModelAttribute("filter") AditionalServiceFilter filter){
		aditionalServiceService.delete(id);
		return "redirect:/subuser/aditionalSubService"+getParams(pageable, filter);
	}
	
	@PostMapping
	public String save(@ModelAttribute("aditionalService") @Valid AditionalService aditionalService, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") AditionalServiceFilter filter) {
		if(br.hasErrors()) return show(model, pageable, filter);
		aditionalServiceService.save(aditionalService);
		status.setComplete();
		return "redirect:/subuser/aditionalSubService"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, AditionalServiceFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder builder = new StringBuilder(page);
		if(!filter.getType().isEmpty()){
			builder.append("&type=");
			builder.append(filter.getType());
		}
		if(!filter.getHotelNameId().isEmpty()){
			for (Long id : filter.getHotelNameId()) {
				builder.append("&hotelNameId=");
				builder.append(id);
			}
		}
		return builder.toString();
	}
	public static Long getSignedUpUser() {
		final SecurityContext ctx = SecurityContextHolder.getContext();

		if (ctx != null) {
			final Authentication auth = ctx.getAuthentication();

			if (auth != null) {
				final Object principal = auth.getPrincipal();

				if (principal instanceof User) {
					final User au = (User) principal;
					return au.getId();
				}
			}
		}

		return (long) 0;
	}
}
