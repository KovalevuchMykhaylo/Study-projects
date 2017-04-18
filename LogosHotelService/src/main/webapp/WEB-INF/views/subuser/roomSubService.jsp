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
						<li class="active"><a href="/subuser/roomSubService<custom:allParams/>">Room Service</a><span
						class="sr-only">(current)</span></li>
						<li><a href="/subuser/rentSubDate">Rent Date</a></li>
					</sec:authorize>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-horizontal" action="/subuser/roomSubService" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="min, max, hotelName, typeOfRoom, typeOfBathRoom, _hotelNameId, _typeOfRoomId, _typeOfBathRoomId"/>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max"/>
				</div>
			</div>
			<div class="form-group">
				<label class="contol-label col-sm-12">Hotel name</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" items="${hotelNames}" itemValue="id" itemLabel="name" path="hotelNameId"/>
				</div>
			</div>
			<div class="form-group">
				<label class="contol-label col-sm-12">Type of room</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" items="${typeOfRooms}" itemValue="id" itemLabel="type" path="typeOfRoomId"/>
				</div>
			</div>
			<div class="form-group">
				<label class="contol-label col-sm-12">Type of bathroom</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" items="${typeOfBathRooms}" itemValue="id" itemLabel="type" path="typeOfBathRoomId"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Ok</button>
			<a href  = "/subuser/roomSubService/cancel" class="btn btn-primary" >Cancel</a>
		</form:form>
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/subuser/roomSubService" method="POST" modelAttribute="roomService" enctype="multipart/form-data">
					<custom:hiddenInputs excludeParams="hotelName, typeOfRoom, typeOfBathRoom"/>
					<div class="form-group">
    					<label for="hotelName" class="col-sm-2 control-label">Hotel Name</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="hotelName" id="hotelName" items="${hotelNames}" itemValue="id" itemLabel="name"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="roomService" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="roomNumber"/></label>
					</div>
  					<div class="form-group">
    					<label for="roomService" class="col-sm-2 control-label">Room number</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="roomNumber" id="roomService"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="roomService" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="room"/></label>
					</div>
  					<div class="form-group">
    					<label for="roomService" class="col-sm-2 control-label">Rooms in number</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="room" id="roomService"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="roomService" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="price"/></label>
					</div>
  					<div class="form-group">
    					<label for="roomService" class="col-sm-2 control-label">Price</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="price" id="roomService"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="file" class="col-sm-2 control-label">Image</label>
    					<div class="col-sm-10">
      						<input name="file" id="file" type="file">
    					</div>
    				</div>
  					<div class="form-group">
    					<label for="typeOfBathRoom" class="col-sm-2 control-label">Type of bath room</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="typeOfBathRoom" id="typeOfBathRoom" items="${typeOfBathRooms}" itemValue="id" itemLabel="type"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="typeOfRoom" class="col-sm-2 control-label">Type of room</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="typeOfRoom" id="typeOfRoom" items="${typeOfRooms}" itemValue="id" itemLabel="type"/>
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
			<div class="col-md-2 col-xs-2"><h4>Image</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Hotel Name</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Room Number</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Rooms in number</h4></div>
			<div class="col-md-1 col-xs-1"><h4>Price</h4></div>
			<div class="col-md-2 col-xs-2"><h4>Type of bath room</h4></div>
			<div class="col-md-2 col-xs-2"><h4>Type of room</h4></div>
			<div class="col-md-2 col-xs-2"><h4>Options</h4></div>
		</div>
			<c:forEach items="${page.content}" var="roomService">
				<div class="row">
					<div class="col-md-2 col-xs-2"><img src="/images/roomservice/${roomService.id}.jpg?version=${roomService.version}" width="100%"></div>
					<div class="col-md-1 col-xs-1">${roomService.hotelName.name}</div>
					<div class="col-md-1 col-xs-1">${roomService.roomNumber}</div>
					<div class="col-md-1 col-xs-1">${roomService.room}</div>
					<div class="col-md-1 col-xs-1">${roomService.price}</div>
					<div class="col-md-2 col-xs-2">${roomService.typeOfBathRoom.type}</div>
					<div class="col-md-2 col-xs-2">${roomService.typeOfRoom.type}</div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-success" href="/subuser/roomSubService/update/${roomService.id}<custom:allParams/>">update</a>
						<a class="btn btn-danger" href="/subuser/roomSubService/delete/${roomService.id}<custom:allParams/>">delete</a></div>
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
								<custom:sort innerHtml="Price asc" paramValue="price" />
								<custom:sort innerHtml="Price desc" paramValue="price,desc" />
								<custom:sort innerHtml="Hotel name asc" paramValue="hotelName.name" />
								<custom:sort innerHtml="Hotel name desc" paramValue="hotelName.name,desc" />
								<custom:sort innerHtml="Type of room name asc" paramValue="typeOfRoom.type" />
								<custom:sort innerHtml="Type of room name desc" paramValue="typeOfRoom.type,desc" />
								<custom:sort innerHtml="Type of bathroom name asc" paramValue="typeOfBathRoom.type" />
								<custom:sort innerHtml="Type of bathroom name desc" paramValue="typeOfBathRoom.type,desc" />
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