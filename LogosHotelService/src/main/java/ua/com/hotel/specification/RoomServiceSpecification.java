package ua.com.hotel.specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.hotel.dto.filter.RoomServiceFilter;
import ua.com.hotel.entity.RoomService;

public class RoomServiceSpecification implements Specification<RoomService>{
	
private final RoomServiceFilter filter;
	
	private final List<Predicate> predicates = new ArrayList<>();
	
	private final static Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");

	public RoomServiceSpecification(RoomServiceFilter filter) {
		this.filter = filter;
		if(REG.matcher(filter.getMax()).matches()){
			filter.setMaxValue(new BigDecimal(filter.getMax().replace(',', '.')));
		}
		if(REG.matcher(filter.getMin()).matches()){
			filter.setMinValue(new BigDecimal(filter.getMin().replace(',', '.')));
		}
	}
	
	private void filterByHotelName(Root<RoomService> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getHotelNameId().isEmpty()){
			predicates.add(root.get("hotelName").in(filter.getHotelNameId()));
		}
	}
	
	private void filterByTypeOfBathRoom(Root<RoomService> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getTypeOfBathRoomId().isEmpty()){
			predicates.add(root.get("typeOfBathRoom").in(filter.getTypeOfBathRoomId()));
		}
	}
	
	private void filterByTypeOfRoom(Root<RoomService> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getTypeOfRoomId().isEmpty()){
			predicates.add(root.get("typeOfRoom").in(filter.getTypeOfRoomId()));
		}
	}
	
	private void filterByRoomService(Root<RoomService> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getMaxValue()!=null){
			predicates.add(cb.le(root.get("price"), filter.getMaxValue()));
		}
		if(filter.getMinValue()!=null){
			predicates.add(cb.ge(root.get("price"), filter.getMinValue()));
		}
	}
	
	private void fetch(Root<RoomService> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			root.fetch("hotelName");
			root.fetch("typeOfBathRoom");
			root.fetch("typeOfRoom");
		}
	}

	@Override
	public Predicate toPredicate(Root<RoomService> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByRoomService(root, query, cb);
		filterByHotelName(root, query, cb);
		filterByTypeOfBathRoom(root, query, cb);
		filterByTypeOfRoom(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
