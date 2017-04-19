package ua.com.servo.controller.admin;

import static ua.com.servo.util.ParamBuilder.getParams;

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

import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.entity.ForceShaft;
import ua.com.servo.service.ForceShaftService;
import ua.com.servo.validator.ForceShaftValidator;


	@Controller
	@RequestMapping("/admin/forceShaft")
	@SessionAttributes("forceShaft")
	public class ForceShaftController {

		@Autowired
		private ForceShaftService forceShaftService;

		@ModelAttribute("forceShaft")
		public ForceShaftForm getForm() {
			return new ForceShaftForm();
		}
		
		@ModelAttribute("filter")
		public SimpleFilter getFilter() {
			return new SimpleFilter();
		}
		
		@InitBinder("forceShaft")
		protected void bind (WebDataBinder binder){
			binder.setValidator(new ForceShaftValidator(forceShaftService));
		}
		
		@RequestMapping
		public String show(Model model, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
			model.addAttribute("page", forceShaftService.findAll(pegeable, filter));
			return "admin-forceShaft";
		}
		
		@GetMapping ("/update/{id}")
		public String update(@PathVariable Integer id, Model model,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
			model.addAttribute("forceShaft", forceShaftService.findForm(id));
			return show (model, pegeable, filter);
		}

		@RequestMapping ("/delete/{id}")
		public String delete(@PathVariable Integer id,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
			forceShaftService.delete(id);
			return "redirect:/admin/forceShaft"+getParams(pegeable, filter);
	}
		@PostMapping
		public String save(@ModelAttribute ("forceShaft") @Valid ForceShaftForm forceShaftForm, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
			if(br.hasErrors()) return show(model, pegeable, filter);
			forceShaftService.save(forceShaftForm);
			status.setComplete();
			return "redirect:/admin/forceShaft"+getParams(pegeable, filter);
		}
		@RequestMapping("/cancel")
		public String cancel(SessionStatus status){
			status.setComplete();
			return "redirect:/admin/forceShaft";
		}
	}
