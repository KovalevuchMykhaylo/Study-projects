package ua.com.hotel.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.hotel.dto.filter.HotelNameFilter;
import ua.com.hotel.entity.HotelName;

public class HotelNameSpecification implements Specification<HotelName>{
	
private final HotelNameFilter filter;

	public HotelNameSpecification(HotelNameFilter filter) {
	this.filter = filter;
}

	private final List<Predicate> predicates = new ArrayList<>();
	
	private void filterByCity(Root<HotelName> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getCityId().isEmpty()){
			predicates.add(root.get("city").in(filter.getCityId()));
		}
	}
	
//	private void filterByUser(Root<HotelName> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//		if(!filter.getCityId().isEmpty()){
//			predicates.add(root.get("user").in(filter.getUserId()));
//		}
//	}
	
	private void filterByHotelName(Root<HotelName> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getName()!=null){
			predicates.add(cb.like(cb.lower(root.get("name")), filter.getName().toLowerCase()+"%"));
		}
	}
	
	private void fetch(Root<HotelName> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			root.fetch("city");
			root.fetch("user");
		}
	}

	@Override
	public Predicate toPredicate(Root<HotelName> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByHotelName(root, query, cb);
		filterByCity(root, query, cb);
//		filterByUser(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
