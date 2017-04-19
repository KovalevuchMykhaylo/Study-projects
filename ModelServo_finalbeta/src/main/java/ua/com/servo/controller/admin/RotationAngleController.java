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
import ua.com.servo.dto.form.RotationAngleForm;
import ua.com.servo.service.RotationAngleService;
import ua.com.servo.validator.RotationAngleValidator;


	@Controller
	@RequestMapping("/admin/rotationAngle")
	@SessionAttributes("rotationAngle")
	public class RotationAngleController {

		@Autowired
		private RotationAngleService rotationAngleService;

		@ModelAttribute("rotationAngle")
		public RotationAngleForm getForm() {
			return new RotationAngleForm();
		}
		
		@ModelAttribute("filter")
		public SimpleFilter getFilter() {
			return new SimpleFilter();
		}
		
//		@InitBinder("rotationAngle")
//		protected void bind (WebDataBinder binder){
//			binder.setValidator(new RotationAngleValidator(rotationAngleService));
//		}
		
		@RequestMapping
		public String show(Model model){
			model.addAttribute("rotationAngles", rotationAngleService.findAll());
			return "admin-rotationAngle";
		}
		
		@GetMapping ("/update/{id}")
		public String update(@PathVariable Integer id, Model model){
			model.addAttribute("rotationAngle", rotationAngleService.findForm(id));
			return show (model);
		}

		@RequestMapping ("/delete/{id}")
		public String delete(@PathVariable Integer id){
			rotationAngleService.delete(id);
			return "redirect:/admin/rotationAngle";
	}
		@PostMapping
		public String save(@ModelAttribute ("rotationAngle") RotationAngleForm rotationAngleForm, BindingResult br, Model model, SessionStatus status){
			if(br.hasErrors()) return show(model);
			rotationAngleService.save(rotationAngleForm);
			status.setComplete();
			return "redirect:/admin/rotationAngle";
		}
		@RequestMapping("/cancel")
		public String cancel(SessionStatus status){
			status.setComplete();
			return "redirect:/admin/rotationAngle";
		}
	}
