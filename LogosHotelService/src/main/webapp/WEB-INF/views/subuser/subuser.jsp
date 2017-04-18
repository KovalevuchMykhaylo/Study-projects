<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<sec:authorize access="hasRole('ROLE_SUBADMIN') or hasRole('ROLE_ADMIN')">
						<li><a href="/subuser/aditionalSubService">Aditional Service</a></li>
						<li><a href="/subuser/typeOfSubBathRoom">Type Of Bath Room</a></li>
						<li><a href="/subuser/typeOfSubRoom">Type Of Room</a></li>
						<li><a href="/subuser/roomSubService">Room Service</a></li>
						<li><a href="/subuser/rentSubDate">Rent Date</a></li>
					</sec:authorize>
				</ul>
			</div>
			<div class="row">
			<c:forEach items="${hotelNames}" var="hotelName">
				<div>
					<img src="/images/hotelName/${hotelName.id}.jpg?version=${hotelName.version}" width="20%">
					<div>
						<a href="/hotelName/${hotelName.id}" class="btn btn-primary">${hotelName.name}</a>
					</div>
				</div>
			</c:forEach>
			</div>
		</div>
	</nav>
</div>