<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">
	<div class="col-sm-12 col-xs-12 parent">
		<c:forEach items="${modelServos}" var="modelServo">
			<div>
				<img src="/images/modelServo/${modelServo.id}.jpg?version=${modelServo.version}" width="100%">
				<p>${modelServo.name}</p>
				<p>${modelServo.price} грн</p>
				<div>
					<a href="/del/${modelServo.id}" class="btn btn-primary">З корзини</a>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<div class="row">
	<div>
		<a href="/iNeedIt/" class="btn btn-primary">Buy</a>
		<div class="btn-modal-cart total-cost">Total price
					${totalPrice} грн.</div>
	</div>
</div>