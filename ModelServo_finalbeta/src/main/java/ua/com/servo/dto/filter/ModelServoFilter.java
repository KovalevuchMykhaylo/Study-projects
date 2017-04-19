package ua.com.servo.dto.filter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ModelServoFilter {
	
	private String search = "";

	private String max = "";
	
	private String min = "";
	
	private BigDecimal maxValue;
	
	private BigDecimal minValue;
	
	private String name;
	
	private List <Integer> forceShaftIds = new ArrayList<>();
	
	private List<Integer> gearTypeIds = new ArrayList<>();
	
	private List<Integer> poverVoltageIds = new ArrayList<>();
	
	private List<Integer> rotationAngleIds = new ArrayList<>();
	
	private List<Integer> sizeIds = new ArrayList<>();
	
	private List<Integer> speedRotationIds = new ArrayList<>();
	
	private List<Integer> typeIds = new ArrayList<>();
	
	private List<Integer> weightIds = new ArrayList<>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public BigDecimal getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(BigDecimal maxValue) {
		this.maxValue = maxValue;
	}

	public BigDecimal getMinValue() {
		return minValue;
	}

	public void setMinValue(BigDecimal minValue) {
		this.minValue = minValue;
	}

	public List<Integer> getForceShaftIds() {
		return forceShaftIds;
	}

	public void setForceShaftIds(List<Integer> forceShaftIds) {
		this.forceShaftIds = forceShaftIds;
	}

	public List<Integer> getGearTypeIds() {
		return gearTypeIds;
	}

	public void setGearTypeIds(List<Integer> gearTypeIds) {
		this.gearTypeIds = gearTypeIds;
	}

	public List<Integer> getPoverVoltageIds() {
		return poverVoltageIds;
	}

	public void setPoverVoltageIds(List<Integer> poverVoltageIds) {
		this.poverVoltageIds = poverVoltageIds;
	}

	public List<Integer> getRotationAngleIds() {
		return rotationAngleIds;
	}

	public void setRotationAngleIds(List<Integer> rotationAngleIds) {
		this.rotationAngleIds = rotationAngleIds;
	}

	public List<Integer> getSizeIds() {
		return sizeIds;
	}

	public void setSizeIds(List<Integer> sizeIds) {
		this.sizeIds = sizeIds;
	}

	public List<Integer> getSpeedRotationIds() {
		return speedRotationIds;
	}

	public void setSpeedRotationIds(List<Integer> speedRotationIds) {
		this.speedRotationIds = speedRotationIds;
	}

	public List<Integer> getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(List<Integer> typeIds) {
		this.typeIds = typeIds;
	}

	public List<Integer> getWeightIds() {
		return weightIds;
	}

	public void setWeightIds(List<Integer> weightIds) {
		this.weightIds = weightIds;
	}

}
