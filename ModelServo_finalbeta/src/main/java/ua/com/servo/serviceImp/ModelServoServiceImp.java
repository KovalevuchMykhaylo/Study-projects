package ua.com.servo.serviceImp;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.servo.dto.filter.ModelServoFilter;
import ua.com.servo.dto.filter.SimpleFilter;
import ua.com.servo.dto.form.ForceShaftForm;
import ua.com.servo.dto.form.ModelServoForm;
import ua.com.servo.entity.ForceShaft;
import ua.com.servo.entity.ModelServo;
import ua.com.servo.repository.ModelServoRepository;
import ua.com.servo.service.ModelServoService;
import ua.com.servo.service.FileWriter.Folder;
import ua.com.servo.service.FileWriter;
import ua.com.servo.specification.ModelServoSpecification;
@Service
public class ModelServoServiceImp implements ModelServoService{
	
	@Autowired
	ModelServoRepository modelServoRepository;
	@Autowired
	private FileWriter fileWriter;

	@Override
	public void save(ModelServoForm form) {
		ModelServo entity = new ModelServo ();
		MultipartFile file = form.getFile();
		entity.setName(new String((form.getName())));
		entity.setPrice(new BigDecimal(form.getPrice().replace(',', '.')));
		entity.setId(form.getId());
		entity.setForceShaft(form.getForceShaft());
		entity.setGearType(form.getGearType());
		entity.setPowerVoltage(form.getPowerVoltage());
		entity.setSize(form.getSize());
		entity.setSpeedRotation(form.getSpeedRotation());
		entity.setRotationAngle(form.getRotationAngle());
		entity.setType(form.getType());
		entity.setWeight(form.getWeight());
		entity = modelServoRepository.saveAndFlush(entity);
		if(fileWriter.write(Folder.MODELSERVO, file, entity.getId())){
			entity.setVersion(form.getVersion()+1);
			modelServoRepository.save(entity);
		}
		
	}

	@Override
	public List<ModelServo> findAll() {
		return modelServoRepository.findAll();
	}

	@Override
	public ModelServo findOne(int id) {
		return modelServoRepository.findOne(id);
	}

	@Override
	public void delete(int id) {
		modelServoRepository.delete(id);
	}

	@Override
	public ModelServo findByModelServo(String name) {
		return modelServoRepository.findByName(name);
	}

	@Override
	public ModelServo findByName(String name) {
		return modelServoRepository.findByName(name);
	}
	
	@Override
	public ModelServoForm findForm(Integer id) {
		ModelServo entity = findOne(id);
		ModelServoForm form = new ModelServoForm();
		form.setName(String.valueOf(entity.getName()));
		form.setPrice(String.valueOf(entity.getPrice()));
		form.setId(entity.getId());
		form.setForceShaft(entity.getForceShaft());
		form.setGearType(entity.getGearType());
		form.setPowerVoltage(entity.getPowerVoltage());
		form.setType(entity.getType());
		form.setRotationAngle(entity.getRotationAngle());
		form.setSize(entity.getSize());
		form.setWeight(entity.getWeight());
		return form;
	}

	@Override
	public Page<ModelServo> findAll(Pageable pegeable, ModelServoFilter filter) {
		return modelServoRepository.findAll(new ModelServoSpecification(filter), pegeable);
	}
	
	@Override
	public int findCount(int id) {
		Integer count = modelServoRepository.findCount(id);
		if(count==null)return 0;
		return count;
	}

	@Override
	public List<ModelServo> findByUserId(int userId) {
		return modelServoRepository.findByUserId(userId);
	}

}
