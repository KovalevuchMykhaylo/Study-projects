<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h2>HotelName: ${hotelName.name}</h2> <h4>${hotelName.adress} ${hotelName.phoneNumber} ${hotelName.eMail}</h4>
<H3>Services: <c:forEach items = "${aditionalServices}" var = "aditionalService">
	<H5>${aditionalService.type}</H5>
</c:forEach></H3>
<div class="row">
			<div class="col-md-4 col-xs-4"><h3>Room number</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Rooms in number</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Price of number</h3></div>
			</div>
<c:forEach items = "${roomServices}" var="roomService">
			<div class="row">
			<div class="col-md-4 col-xs-4"><h3> <a href="/roomService/${roomService.id}">${roomService.roomNumber}</a></h3></div>
			<div class="col-md-4 col-xs-4"><h3>${roomService.room}</h3></div>
			<div class="col-md-4 col-xs-4"><h3>${roomService.price} UAH</h3></div>
			</div>
</c:forEach>
<c:if test="${empty roomServices}">
	<h3>No room for rent</h3>
</c:if>