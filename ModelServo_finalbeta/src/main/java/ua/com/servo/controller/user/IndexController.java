package ua.com.servo.controller.user;

import java.math.BigDecimal;
import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.servo.dto.filter.ModelServoFilter;
import ua.com.servo.entity.ModelServo;
import ua.com.servo.entity.User;
import ua.com.servo.service.ModelServoService;
import ua.com.servo.service.RotationAngleService;
import ua.com.servo.service.SizeService;
import ua.com.servo.service.UserService;
import ua.com.servo.service.WeightService;
import ua.com.servo.validator.UserValidator;

@Controller
public class IndexController {


	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelServoService modelServoService;
	
	@Autowired
	private SizeService sizeService;
	@Autowired
	private WeightService weightService;
	@Autowired
	private RotationAngleService rotationAngleService; 
	
//	@RequestMapping("/")
//	public String index(Principal principal){
//		if(principal!=null){
//			System.out.println(principal.getName());
//		}
//		return "user-index";
//	}
	@RequestMapping("/")
	public String index(Model model, @CookieValue(defaultValue="0", name="userId") int id, HttpServletResponse response, @PageableDefault Pageable pageable, @ModelAttribute("filter") ModelServoFilter filter){
		if(id==0){
			id = userService.createNewUser();
			response.addCookie(new Cookie("userId", String.valueOf(id)));
		}
		model.addAttribute("page", modelServoService.findAll(pageable, filter));
		model.addAttribute("sizes", sizeService.findAll());
		model.addAttribute("weights", weightService.findAll());
		model.addAttribute("rotationAngles", rotationAngleService.findAll());
		return "user-index";
	}
	@ModelAttribute("filter")
	public ModelServoFilter getFilter(){
		return new ModelServoFilter();
	}
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
	}
	@GetMapping("/shopping")
	public String shopping(Model model, @CookieValue(defaultValue = "0", name = "userId") int userId) {
		model.addAttribute("modelServos", modelServoService.findByUserId(userId));
			BigDecimal totalPrice = new BigDecimal(0); 
			for (ModelServo modelServo : modelServoService.findByUserId(userId)) { 
			totalPrice = totalPrice.add(modelServo.getPrice()); 
			model.addAttribute("totalPrice", totalPrice);
		}
		return "user-shopping";
	}
	
	@GetMapping("/buy/{modelServoId}")
	public String buy(@CookieValue(defaultValue="0", name="userId") int userId, @PathVariable int modelServoId){
		userService.addToShoppingCart(userId, modelServoId);
		return "redirect:/";
	}
	
	@GetMapping("/del/{modelServoId}")
	public String remove(@CookieValue(defaultValue = "0", name = "userId") int userId, @PathVariable int modelServoId) {
		userService.removeToShoppingCart(userId, modelServoId);
		return "redirect:/shopping";
	}
	
	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "user-registration";
	}
	
	@PostMapping("/registration")
	public String save(@Valid User user, BindingResult br) {
		if (br.hasErrors())return "user-registration";
		userService.save(user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(){
		return "user-login";
}
	@GetMapping("/iNeedIt")
	public String iNeedIt(@CookieValue(defaultValue = "0", name = "userId") int userId, Principal principal) {
		userService.sendMail("ModelServo", principal.getName(), "You buy shit!!!");
		userService.removeAllToShoppingCart(userId);
		return "user-success";
	}
	

}