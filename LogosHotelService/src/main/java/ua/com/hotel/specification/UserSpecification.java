package ua.com.hotel.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.hotel.dto.filter.UserFilter;
import ua.com.hotel.entity.User;

public class UserSpecification implements Specification<User>{
	
private final UserFilter filter;

	public UserSpecification(UserFilter filter) {
	this.filter = filter;
}

	private final List<Predicate> predicates = new ArrayList<>();
	
	private void filterByHotelName(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getHotelNamesId().isEmpty()){
			predicates.add(root.join("hotelNames").in(filter.getHotelNamesId()));
		}
	}
	
//	private void filterByRentDate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//		if(!filter.getRentDatesId().isEmpty()){
//			predicates.add(root.get("rentDate").in(filter.getRentDatesId()));
//		}
//	}
	
//	private void filterByName(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb){
//		if(filter.getName()!=null){
//			predicates.add(cb.like(cb.lower(root.get("name")), filter.getName().toLowerCase()+"%"));
//		}
//	}
	
	private void fetch(Root<User> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			root.fetch("hotelNames");
//			root.fetch("rentDates");
		}
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByHotelName(root, query, cb);
//		filterByRentDate(root, query, cb);
//		filterByName(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}
