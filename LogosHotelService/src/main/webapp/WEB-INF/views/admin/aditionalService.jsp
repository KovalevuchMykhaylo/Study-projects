<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
				<li class="active"><a href="/admin/aditionalService<custom:allParams/>">Aditional Service</a><span
						class="sr-only">(current)</span></li>
					<li><a href="/admin/city">City</a></li>
					<li><a href="/admin/hotelName">Hotel Name</a></li>
					<li><a href="/admin/typeOfBathRoom">Type Of Bath Room</a></li>
					<li><a href="/admin/typeOfRoom">Type Of Room</a></li>
					<li><a href="/admin/roomService">Room Service</a></li>
					<li><a href="/admin/rentDate">Rent Date</a></li>
					<li><a href="/admin/user">Users</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-horizontal" action="/admin/aditionalService" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="type, hotelNameId, _hotelNameId"/>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="type" class="form-control" placeholder="Type"/>
				</div>
			</div>
			<div class="form-group">
				<label class="contol-label col-sm-12">Hotel Name</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" items="${hotelNames}" itemValue="id" itemLabel="name" path="hotelNameId"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Ok</button>
			<a href  = "/admin/aditionalService/cancel" class="btn btn-primary" >Cancel</a>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/aditionalService" method="POST" modelAttribute="aditionalService">
					<custom:hiddenInputs excludeParams="type, hotelNameId"/>
					<div class="form-group">
						<label for="type" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="type"/></label>
					</div>
					<div class="form-group">
    					<label for="hotelName" class="col-sm-2 control-label">Hotel Name</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="hotelName" id="hotelName" items="${hotelNames}" itemValue="id" itemLabel="name"/>
    					</div>
  					</div> 	 
					<div class="form-group">
    					<label for="aditionalService" class="col-sm-2 control-label">Description of aditional services</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="type" id="name"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-primary">Create</button>
      						<a href  = "/admin/aditionalService/cancel" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-xs-3"><h3>Hotel Name</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Aditional Service</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Update</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="aditionalService">
				<div class="row">
					<div class="col-md-3 col-xs-3">${aditionalService.hotelName.name}</div>
					<div class="col-md-3 col-xs-3">${aditionalService.type}</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-success" href="/admin/aditionalService/update/${aditionalService.id}<custom:allParams/>">update</a></div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-danger" href="/admin/aditionalService/delete/${aditionalService.id}<custom:allParams/>">delete</a></div>
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
								<custom:sort innerHtml="Aditional service asc" paramValue="type" />
								<custom:sort innerHtml="Aditional service desc" paramValue="type,desc" />
								<custom:sort innerHtml="Hotel name asc" paramValue="hotelName.name" />
								<custom:sort innerHtml="Hotel name desc" paramValue="hotelName.name,desc" />
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
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>