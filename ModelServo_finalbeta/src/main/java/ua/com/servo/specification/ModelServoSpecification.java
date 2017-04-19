package ua.com.servo.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.servo.dto.filter.ModelServoFilter;
import ua.com.servo.entity.ModelServo;

public class ModelServoSpecification implements Specification<ModelServo> {

private final ModelServoFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private final static Pattern PATTERN = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	
	public ModelServoSpecification(ModelServoFilter filter) {
		this.filter = filter;
		if(PATTERN.matcher(filter.getMax()).matches()){
			filter.setMaxValue(new BigDecimal(filter.getMax().replace(',', '.')));
		}
		if(PATTERN.matcher(filter.getMin()).matches()){
			filter.setMinValue(new BigDecimal(filter.getMin().replace(',', '.')));
		}
	}
	
	private void filterByForceShaft(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getForceShaftIds().isEmpty()){
			predicates.add(root.get("forceShaft").in(filter.getForceShaftIds()));
		}
	}
	
	private void filterByGearType(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getGearTypeIds().isEmpty()){
			predicates.add(root.get("gearType").in(filter.getGearTypeIds()));
		}
	}
	
	private void filterByPowerVoltage(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getPoverVoltageIds().isEmpty()){
			predicates.add(root.get("powerVoltage").in(filter.getGearTypeIds()));
		}
	}
	
	private void filterByRotationAngle(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getRotationAngleIds().isEmpty()){
			predicates.add(root.get("rotationAngle").in(filter.getRotationAngleIds()));
		}
	}
	private void filterBySize(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSizeIds().isEmpty()){
			predicates.add(root.get("size").in(filter.getSizeIds()));
		}
	}
	
	private void filterBySpeedRotation(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getSpeedRotationIds().isEmpty()){
			predicates.add(root.get("speedRotation").in(filter.getSpeedRotationIds()));
		}
	}
	
	private void filterByType(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getTypeIds().isEmpty()){
			predicates.add(root.get("type").in(filter.getTypeIds()));
		}
	}
	
	private void filterByWeight(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getWeightIds().isEmpty()){
			predicates.add(root.get("weight").in(filter.getWeightIds()));
		}
	}
	
	private void filterBySearch(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		if (!filter.getSearch().isEmpty()) {
			predicates.add(cb.like(root.get("name"), filter.getSearch() + "%"));
		}
	}
	
	private void filterByPrice(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
	}
	
	private void fetch(Root<ModelServo> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			query.distinct(true);
			root.fetch("forceShaft");
			root.fetch("gearType");
			root.fetch("powerVoltage");
			root.fetch("rotationAngle");
			root.fetch("size");
			root.fetch("speedRotation");
			root.fetch("type");
			root.fetch("weight");
		}
	}

	@Override
	public Predicate toPredicate(Root<ModelServo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByForceShaft(root, query, cb);
		filterByGearType(root, query, cb);
		filterByPowerVoltage(root, query, cb);
		filterByPrice(root, query, cb);
		filterByRotationAngle(root, query, cb);
		filterBySize(root, query, cb);
		filterBySpeedRotation(root, query, cb);
		filterByType(root, query, cb);
		filterByWeight(root, query, cb);
		filterBySearch(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
}


}
