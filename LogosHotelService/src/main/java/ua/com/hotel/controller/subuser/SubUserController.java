package ua.com.hotel.controller.subuser;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.hotel.entity.User;
import ua.com.hotel.service.HotelNameService;

@Controller
public class SubUserController {

	@Autowired
	private HotelNameService hotelNameService;

	@RequestMapping("/subuser{id}")
	public String subuser(Model model, @PathVariable Long id, Principal principal) {
		model.addAttribute("hotelNames", hotelNameService.findAllBySubUser(getSignedUpUser()));
		return "subuser-subuser";
	}
//Метод повертає айді користувач
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
