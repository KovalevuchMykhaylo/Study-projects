package ua.com.hotel.controller.user;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.hotel.dto.filter.UserFilter;
import ua.com.hotel.editor.HotelNameEditor;
import ua.com.hotel.entity.HotelName;
import ua.com.hotel.entity.User;
import ua.com.hotel.service.AditionalServiceService;
import ua.com.hotel.service.CityService;
import ua.com.hotel.service.HotelNameService;
import ua.com.hotel.service.RentDateService;
import ua.com.hotel.service.RoomServiceService;
import ua.com.hotel.service.TypeOfBathRoomService;
import ua.com.hotel.service.TypeOfRoomService;
import ua.com.hotel.service.UserService;
import ua.com.hotel.validator.UserValidator;

@Controller
public class IndexController {

	@Autowired
	private CityService cityService;

	@Autowired
	private HotelNameService hotelNameService;

	@Autowired
	private RoomServiceService roomServiceService;

	@Autowired
	private TypeOfBathRoomService typeOfBathRoomService;

	@Autowired
	private TypeOfRoomService typeOfRoomService;

	@Autowired
	private AditionalServiceService aditionalServiceService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private RentDateService rentDateService;
//Обробка початкової сторінки
	@RequestMapping("/")
	public String index(Principal principal, Model model){
		model.addAttribute("users", userService.findAll());
		model.addAttribute("HotelNames", hotelNameService.findAll());
		model.addAttribute("citys", cityService.findAll());
		if(principal!=null){
			System.out.println(principal.getName());
		}
		return "user-index";
	}
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
		binder.registerCustomEditor(HotelName.class, new HotelNameEditor(hotelNameService));
	}

	@RequestMapping("/city/{id}")
	public String category(@PathVariable Long id, Model model) {
		model.addAttribute("city", cityService.findOne(id));
		model.addAttribute("hotelNames", hotelNameService.findByCityId(id));
		return "user-city";
	}

	@RequestMapping("/hotelName/{id}")
	public String hotelInCity(@PathVariable Long id, Model model) {
		model.addAttribute("hotelName", hotelNameService.findOne(id));
		model.addAttribute("roomServices", roomServiceService.findByHotelNameId(id));
		model.addAttribute("aditionalServices",	aditionalServiceService.findByHotelNameId(id));
		return "user-hotelName";
	}

	@PostMapping("/registration")
	public String save(@Valid User user, BindingResult br) {
		if (br.hasErrors())return "user-registration";
		userService.save(user);
		return "redirect:/login";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "user-registration";
	}
//Реєстрація користувача з разшириними правами
	@PostMapping("/registrationSub")
	public String saveSubUser(User user, BindingResult br) {
		userService.saveSubUser(user);
		return "redirect:/login";
	}

	@GetMapping("/registrationSub")
	public String registrationSubUser(Model model) {
		model.addAttribute("hotelNames", hotelNameService.findAll());
		model.addAttribute("user", new User());
		return "user-registrationSub";
	}

	@GetMapping("/login")
	public String login() {
		return "user-login";
	}
//Профіль користувача, відображає дати
	@RequestMapping("/myProfile/{id}")
	public String myProfile(@PathVariable Long id, Model model, UserFilter userFilter) {
		model.addAttribute("user", userService.findOne(id));
		model.addAttribute("rentDates", rentDateService.findAllByUser(getSignedUpUser()));
		return "user-myProfile";
	}
	
	@RequestMapping("/cancel")
	public String cancel() {
		return "redirect:/registration";
	}
	
	@RequestMapping("/cancelsub")
	public String cancelsub() {
		return "redirect:/registrationSub";
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