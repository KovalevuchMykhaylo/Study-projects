package ua.com.hotel.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ua.com.hotel.dto.filter.AditionalServiceFilter;
import ua.com.hotel.entity.AditionalService;

public class AditionalServiceSpecification implements Specification<AditionalService>{
	
	private final AditionalServiceFilter filter;

	public AditionalServiceSpecification(AditionalServiceFilter filter) {
	this.filter = filter;
}

	private final List<Predicate> predicates = new ArrayList<>();
	
	private void filterByHotelName(Root<AditionalService> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(!filter.getHotelNameId().isEmpty()){
			predicates.add(root.get("hotelName").in(filter.getHotelNameId()));
		}
	}
	
	private void filterByAditionalService(Root<AditionalService> root, CriteriaQuery<?> query, CriteriaBuilder cb){
		if(filter.getType()!=null){
			predicates.add(cb.like(cb.lower(root.get("type")), filter.getType().toLowerCase()+"%"));
		}
	}
	
	private void fetch(Root<AditionalService> root, CriteriaQuery<?> query){
		if(!query.getResultType().equals(Long.class)){
			root.fetch("hotelName");
		}
	}

	@Override
	public Predicate toPredicate(Root<AditionalService> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		fetch(root, query);
		filterByHotelName(root, query, cb);
		filterByAditionalService(root, query, cb);
		if(predicates.isEmpty())return null;
		Predicate[] array = new Predicate[predicates.size()];
		array = predicates.toArray(array);
		return cb.and(array);
	}

}

