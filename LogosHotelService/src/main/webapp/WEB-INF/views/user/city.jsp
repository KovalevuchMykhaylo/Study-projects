<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>City: ${city.name}</h2>
<c:forEach items="${hotelNames}" var="hotelName">
	<li><a href="/hotelName/${hotelName.id}">${hotelName.name}</a></li>
</c:forEach>
<c:if test="${empty hotelNames}">
	<h3>Hotel not found</h3>
</c:if>
<div class="col-md-6 col-xs-12 parent">
	<c:forEach items="${hotelNames}" var="hotelName">
		<div>
			<img src="/images/hotelName/${hotelName.id}.jpg?version=${hotelName.version}" width="100%">
			<div>
				<a href="/hotelName/${hotelName.id}" class="btn btn-primary">${hotelName.name}</a>
			</div>
		</div>
	</c:forEach>
</div>