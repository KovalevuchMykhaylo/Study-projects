package ua.com.servo.controller.admin;

import javax.validation.Valid;

import org.hibernate.id.PersistentIdentifierGenerator;
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
import ua.com.servo.entity.Size;
import ua.com.servo.service.SizeService;
import ua.com.servo.validator.SizeValidator;
import static ua.com.servo.util.ParamBuilder.*;
@Controller
@RequestMapping("/admin/size")
@SessionAttributes("size")
public class SizeConroller {

	@Autowired
	private SizeService sizeService;

	@ModelAttribute("size")
	public Size getForm() {
		return new Size();
	}
	
	@ModelAttribute("filter")
	public SimpleFilter getFilter() {
		return new SimpleFilter();
	}
	
	@InitBinder("size")
	protected void bind (WebDataBinder binder){
		binder.setValidator(new SizeValidator(sizeService));
	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", sizeService.findAll(pegeable, filter));
		return "admin-size";
	}
	
	@GetMapping ("/update/{id}")
	public String update(@PathVariable Integer id, Model model,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("size", sizeService.findOne(id));
		return show (model, pegeable, filter);
	}

	@RequestMapping ("/delete/{id}")
	public String delete(@PathVariable Integer id,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		sizeService.delete(id);
		return "redirect:/admin/size"+getParams(pegeable, filter);
}
	@PostMapping
	public String save(@ModelAttribute ("size") @Valid Size size, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") SimpleFilter filter){
		if(br.hasErrors()) return show(model, pegeable, filter);
		sizeService.save(size);
		status.setComplete();
		return "redirect:/admin/size"+getParams(pegeable, filter);
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/size";
	}
}