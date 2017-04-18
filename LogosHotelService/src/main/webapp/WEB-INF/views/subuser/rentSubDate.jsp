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
					<sec:authorize access="hasRole('ROLE_SUBADMIN') or hasRole('ROLE_ADMIN')">
						<li><a href="/subuser/aditionalSubService">Aditional Service</a></li>
						<li><a href="/subuser/typeOfSubBathRoom">Type Of Bath Room</a></li>
						<li><a href="/subuser/typeOfSubRoom">Type Of Room</a></li>
						<li><a href="/subuser/roomSubService">Room Service</a></li>
						<li class="active"><a href="/subuser/rentSubDate<custom:allParams/>">Rent Date</a><span
						class="sr-only">(current)</span></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/subuser/roomSubService" method="POST" modelAttribute="rentDate">
<!--   					<div class="form-group"> -->
<!--     					<label for="roomService" class="col-sm-2 control-label">Hotel Name</label> -->
<!--     					<div class="col-sm-10"> -->
<%--       						<form:select class="form-control" path="roomService" id="roomService" items="${hotelName}" itemValue="id" itemLabel="name"/> --%>
<!--     					</div> -->
<!--   					</div> -->
  					<div class="form-group">
    					<label for="roomService" class="col-sm-2 control-label">Room number</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="roomService" id="roomService" items="${roomService}" itemValue="id" itemLabel="roomNumber"/>
    					</div>
  					</div>
					<div class="form-group">
						<label for="rentDate" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="first"/></label>
					</div>
					<div class="form-group">
    					<label for="rentDate" class="col-sm-2 control-label">First</label>
    					<div class="col-sm-10">
<%--     						<form:input type="text" class="form-control" path="first" id="rentDate" placeholder="mm/dd/yy"/> --%>
    						<form:input type="text" class="form-control" path="first" id="rentDate" readonly="readonly" size="10" onclick="showcalendar(this)"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="rentDate" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="second"/></label>
					</div>
  					<div class="form-group">
    					<label for="rentDate" class="col-sm-2 control-label">Second</label>
    					<div class="col-sm-10">
<%--     						<form:input type="text" class="form-control" path="second" id="rentDate" placeholder="mm/dd/yy"/> --%>
    						<form:input type="text" class="form-control" path="second" id="rentDate" readonly="readonly" size="10" onclick="showcalendar(this)"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-primary">Create</button>
      						<a href  = "/subuser/roomSubService/cancel" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3 col-xs-3"><h3>First date</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Second date</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Room number</h3></div>
<!-- 			<div class="col-md-2 col-xs-2"><h3>User</h3></div> -->
<!-- 			<div class="col-md-2 col-xs-2"><h3>Hotel name</h3></div> -->
			<div class="col-md-2 col-xs-2"><h3>Update</h3></div>
			<div class="col-md-2 col-xs-2"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="rentDate">
				<div class="row">
					<div class="col-md-3 col-xs-3">${rentDate.first}</div>
					<div class="col-md-3 col-xs-3">${rentDate.second}</div>
					<div class="col-md-2 col-xs-2">${rentDate.roomService.roomNumber}</div>
<%-- 					<div class="col-md-2 col-xs-2">${rentDate.user}</div> --%>
<%-- 					<div class="col-md-2 col-xs-2">${rentDate.roomService.hotelName}</div> --%>
					<div class="col-md-2 col-xs-2"><a class="btn btn-success" href="/subuser/roomSubService/update/${rentDate.id}<custom:allParams/>">update</a></div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/subuser/roomSubService/delete/${rentDate.id}<custom:allParams/>">delete</a></div>
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
								<custom:sort innerHtml="Date asc" paramValue="first" />
								<custom:sort innerHtml="Date desc" paramValue="first,desc" />
								<custom:sort innerHtml="Date asc" paramValue="second" />
								<custom:sort innerHtml="Date desc" paramValue="second,desc" />
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