package ua.com.servo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class AdminConroller {
	
		@RequestMapping("/admin")
		public String admin() {
			return "admin-admin";
		}
	}
