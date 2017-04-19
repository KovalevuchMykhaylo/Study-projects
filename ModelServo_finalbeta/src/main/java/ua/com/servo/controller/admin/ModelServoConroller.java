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

import ua.com.servo.util.ParamBuilder;
import ua.com.servo.dto.filter.ModelServoFilter;
import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.ModelServoForm;
import ua.com.servo.editor.ForceShaftEditor;
import ua.com.servo.editor.GearTypeEditor;
import ua.com.servo.editor.ModelServoEditor;
import ua.com.servo.editor.PowerVoltageEditor;
import ua.com.servo.editor.RotationAngleEditor;
import ua.com.servo.editor.SizeEditor;
import ua.com.servo.editor.SpeedRotationEditor;
import ua.com.servo.editor.TypeEditor;
import ua.com.servo.editor.WeightEditor;
import ua.com.servo.entity.ForceShaft;
import ua.com.servo.entity.GearType;
import ua.com.servo.entity.ModelServo;
import ua.com.servo.entity.PowerVoltage;
import ua.com.servo.entity.RotationAngle;
import ua.com.servo.entity.Size;
import ua.com.servo.entity.SpeedRotation;
import ua.com.servo.entity.Type;
import ua.com.servo.entity.Weight;
import ua.com.servo.service.ForceShaftService;
import ua.com.servo.service.GearTypeService;
import ua.com.servo.service.ModelServoService;
import ua.com.servo.service.PowerVoltageService;
import ua.com.servo.service.RotationAngleService;
import ua.com.servo.service.SizeService;
import ua.com.servo.service.SpeedRotationService;
import ua.com.servo.service.TypeService;
import ua.com.servo.service.WeightService;
import ua.com.servo.validator.ModelServoValitator;

@Controller
@RequestMapping("/admin/modelServo")
@SessionAttributes("modelServo")
public class ModelServoConroller {

	@Autowired
	private ModelServoService modelServoService;

	@Autowired
	private SizeService sizeService;

	@Autowired
	private SpeedRotationService speedRotationService;
	
	@Autowired
	private GearTypeService gearTypeService;

	@Autowired
	private RotationAngleService rotationAngleService;

	@Autowired
	private ForceShaftService forceShaftService;
	
	@Autowired
	private PowerVoltageService powerVoltageService;
	
	@Autowired
	private TypeService typeService;

	@Autowired
	private WeightService weightService;

	@InitBinder("modelServo")
	protected void bind(WebDataBinder binder) {
		binder.setValidator(new ModelServoValitator(modelServoService));
		binder.registerCustomEditor(ForceShaft.class, new ForceShaftEditor(forceShaftService));
		binder.registerCustomEditor(GearType.class, new GearTypeEditor(gearTypeService));
		binder.registerCustomEditor(PowerVoltage.class, new PowerVoltageEditor(powerVoltageService));
		binder.registerCustomEditor(RotationAngle.class, new RotationAngleEditor(rotationAngleService));
		binder.registerCustomEditor(Size.class, new SizeEditor(sizeService));
		binder.registerCustomEditor(SpeedRotation.class, new SpeedRotationEditor(speedRotationService));
		binder.registerCustomEditor(Type.class, new TypeEditor(typeService));
		binder.registerCustomEditor(Weight.class, new WeightEditor(weightService));
		binder.registerCustomEditor(ModelServo.class, new ModelServoEditor(modelServoService));
	}

	@ModelAttribute("modelServo")
	public ModelServoForm getForm() {
		return new ModelServoForm();
	}

	@GetMapping
	public String show (Model model, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") ModelServoFilter filter){
		model.addAttribute("page", modelServoService.findAll(pegeable, filter));
		model.addAttribute("forceShafts", forceShaftService.findAll());
		model.addAttribute("gearTypes", gearTypeService.findAll());
		model.addAttribute("powerVoltages", powerVoltageService.findAll());
		model.addAttribute("rotationAngles", rotationAngleService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		model.addAttribute("speedRotations", speedRotationService.findAll());
		model.addAttribute("types", typeService.findAll());
		model.addAttribute("weights", weightService.findAll());
		
		return "admin-modelServo";
	}

		
	@GetMapping ("/update/{id}")
	public String update(@PathVariable Integer id, Model model,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") ModelServoFilter filter){
		model.addAttribute("modelServo", modelServoService.findForm(id));
		return show (model, pegeable, filter);
	}

	@RequestMapping ("/delete/{id}")
	public String delete(@PathVariable Integer id,  @PageableDefault Pageable pegeable,  @ModelAttribute("filter") ModelServoFilter filter){
		modelServoService.delete(id);
		return "redirect:/admin/modelServo"+getParams(pegeable, filter);
}
	@PostMapping
	public String save(@ModelAttribute ("modelServo") @Valid ModelServoForm modelServoForm, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pegeable,  @ModelAttribute("filter") ModelServoFilter filter){
		System.out.println("Hello");
		if(br.hasErrors()) return show(model, pegeable, filter);
		System.out.println("Hello2");
		modelServoService.save(modelServoForm);
		System.out.println("Hello3");
		status.setComplete();
		return "redirect:/admin/modelServo"+getParams(pegeable, filter);
	}
	@RequestMapping("/cancel")
	public String cancel(SessionStatus status){
		status.setComplete();
		return "redirect:/admin/modelServo";
	}
	private String getParams(Pageable pageable, ModelServoFilter filter){
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
		if(!filter.getForceShaftIds().isEmpty()){
			for (Integer id : filter.getForceShaftIds()) {
				builder.append("&forceShaftIds=");
				builder.append(id);
			}
		}
		if(!filter.getPoverVoltageIds().isEmpty()){
			for (Integer id : filter.getPoverVoltageIds()) {
				builder.append("&getPoverVoltageIds=");
				builder.append(id);
			}
		}
		if(!filter.getGearTypeIds().isEmpty()){
			for (Integer id : filter.getGearTypeIds()) {
				builder.append("&getGearTypeIds=");
				builder.append(id);
			}
		}
		if(!filter.getRotationAngleIds().isEmpty()){
			for (Integer id : filter.getRotationAngleIds()) {
				builder.append("&getRotationAngleIds=");
				builder.append(id);
			}
		}
		if(!filter.getSpeedRotationIds().isEmpty()){
			for (Integer id : filter.getSpeedRotationIds()) {
				builder.append("&getSpeedRotationIds=");
				builder.append(id);
			}
		}
		if(!filter.getGearTypeIds().isEmpty()){
			for (Integer id : filter.getGearTypeIds()) {
				builder.append("&getGearTypeIds=");
				builder.append(id);
			}
		}
		return builder.toString();
	}
}


