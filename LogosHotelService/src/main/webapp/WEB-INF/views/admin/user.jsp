<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
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
					<li><a href="/admin/aditionalService">Aditional Service</a></li>
					<li><a href="/admin/city">City</a></li>
					<li><a href="/admin/hotelName">Hotel Name</a></li>
					<li><a href="/admin/typeOfBathRoom">Type Of Bath Room</a></li>
					<li><a href="/admin/typeOfRoom">Type Of Room</a></li>
					<li><a href="/admin/roomService">Room Service</a></li>
					<li><a href="/admin/rentDate">Rent Date</a></li>
					<li class="active"><a href="/admin/user<custom:allParams/>">Users</a><span
						class="sr-only">(current)</span></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
<div class="col-md-3 col-xs-12">
<form:form class="form-inline" action="/admin/user" method="GET" modelAttribute="filter">
<custom:hiddenInputs excludeParams="_hotelNames"/>
	<div class="form-group">
				<label class="contol-label col-sm-12">Hotel name</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" items="${hotelNames}" itemValue="id" itemLabel="name" path="hotelNamesId"/>
					<a href  = "/admin/user/cancel" class="btn btn-primary" >Cancel</a>
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
			</div>
		</form:form>
</div>
<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/user" method="POST" modelAttribute="users">
					<custom:hiddenInputs excludeParams="name, email, password, hotelNames, rentDate, _name, _email, _password, _hotelNamesId, _rentDate"/>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>Email</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Name</h3></div>
<!-- 			<div class="col-md-3 col-xs-3"><h3>Hotel</h3></div> -->
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="user">
				<div class="row">
							<div class="col-md-4 col-xs-4">${user.email}</div>
							<div class="col-md-4 col-xs-4">${user.name}</div>
<%-- 						<c:forEach items="${user.hotelNames}" var="user"> --%>
<%-- 							<div class="col-md-3 col-xs-3">${hotelNames}</div> --%>
<%-- 						</c:forEach> --%>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/user/delete/${user.id}<custom:allParams/>">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="row">
					<div class="col-md-6 col-xs-6 text-center">
						<div class="dropdown">
							<button class="btn btn-primary dropdown-toggle" type="button"
								data-toggle="dropdown">
								Sort <span class="caret"></span>
							</button>
							<ul class="dropdown-menu">
								<custom:sort innerHtml="Name asc" paramValue="name" />
								<custom:sort innerHtml="Name desc" paramValue="name,desc" />
							</ul>
						</div>
					</div>
					<div class="col-md-6 col-xs-6 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
				</div>
	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>