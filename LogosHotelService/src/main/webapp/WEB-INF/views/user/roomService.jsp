<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row">
	<div class="col-md-2 col-xs-2"><h3>Room number</h3></div>
	<div class="col-md-2 col-xs-2"><h3>Rooms in number</h3></div>
	<div class="col-md-2 col-xs-2"><h3>Price of number</h3></div>
	<div class="col-md-3 col-xs-3"><h3>Type of bath room</h3></div>
	<div class="col-md-3 col-xs-3"><h3>Type of room</h3></div>
</div>
<div class="row">
	<div class="col-md-2 col-xs-2"><h3>${roomServices.roomNumber}</h3></div>
	<div class="col-md-2 col-xs-2"><h3>${roomServices.room}</h3></div>
	<div class="col-md-2 col-xs-2"><h3>${roomServices.price} UAH</h3></div>
	<div class="col-md-3 col-xs-3"><h3>${roomServices.typeOfBathRoom.type}</h3></div>
	<div class="col-md-2 col-xs-2"><h3>${roomServices.typeOfRoom.type}</h3></div>
	</div>
<div class="row">
<sec:authorize access="!isAuthenticated()">
<div class="form-group"><h3 class="col-md-offset-4 col-md-4">To rent the room you need to register!!!</h3></div>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<div class="col-md-7 col-xs-12"><a href='#' id='trigger'><button>Rent</button></a>
		 <div id='box' style='display: none;padding:10px;background-color:#f1f1f1;margin:10px;width:50%;'>
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/roomService/${roomServices.id}" method="POST" modelAttribute="rentDate">
<!-- 					<div class="form-group"> -->
<!--     					<label for="roomServices" class="col-sm-2 control-label">Room number</label> -->
<%--     					<div class="col-md-2 col-xs-2"><h3>${roomServices.id}</h3></div> --%>
    					<form:hidden path="roomService" value="${roomServices.id}" label="roomNumber"/>
<%--     					<form:hidden path="user" value="${user.id}" label="userId"/> --%>
<%-- 	<form:hidden path="roomService" id="roomService" items="${roomServices}" itemValue="roomService" itemLabel="roomNumber"/> --%>
<!--   					</div> -->
<!-- 					<div class="form-group"> -->
<!--     					<label for="roomService" class="col-sm-2 control-label">Room number</label> -->
<!--     					<div class="col-sm-10"> -->
<%--       						<form:select class="form-control" path="roomService" id="roomService" items="${roomServices}" itemValue="id" itemLabel="roomNumber"/> --%>
<!--     					</div> -->
<!--   					</div> -->
					<div class="form-group">
						<label for="rentDate" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="first"/></label>
					</div>
					<div class="form-group">
    					<label for="rentDate" class="col-sm-4 control-label">Incoming Date</label>
    					<div class="col-sm-8">
    					<label for="rentDate"></label><input type="text" id="rentDate" name="first" readonly="readonly" size="10" onclick="showcalendar(this)"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="rentDate" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="second"/></label>
					</div>
  					<div class="form-group">
    					<label for="rentDate" class="col-sm-4 control-label">Leaving Date</label>
    					<div class="col-sm-8">
    					<label for="rentDate"></label><input type="text" id="rentDate" name="second" readonly="readonly" size="10" onclick="showcalendar(this)"/>
    					</div>
  					</div>
<%--   					<sec:authorize access="!isAuthenticated()"> --%>
<!--   					<div class="form-group"> -->
<!--     					<label for="" class="col-sm-2 control-label">Name</label> -->
<!--     					<div class="col-sm-10"> -->
<!--       						<input type="text" class="form-control" name="" id=""/> -->
<!--     					</div> -->
<!--   					</div> -->
<!--   					<div class="form-group"> -->
<!--     					<label for="" class="col-sm-2 control-label">Email</label> -->
<!--     					<div class="col-sm-10"> -->
<!--       						<input type="text" class="form-control" name="" id=""/> -->
<!--     					</div> -->
<!--   					</div> -->
<!--   					<div class="form-group"> -->
<!--     					<label for="" class="col-sm-2 control-label">Phone Number</label> -->
<!--     					<div class="col-sm-10"> -->
<!--       						<input type="text" class="form-control" name="" id=""/> -->
<!--     					</div> -->
<!--   					</div> -->
<!--   					<div class="form-group"> -->
<!--     					<label for="" class="col-sm-2 control-label">Password</label> -->
<!--     					<div class="col-sm-10"> -->
<!--       						<input type="text" class="form-control" name="" id=""/> -->
<!--     					</div> -->
<!--   					</div> -->
<%--   					</sec:authorize> --%>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-primary">Rent</button>
      						<a href  = "/roomService/${roomServices.id}" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
	 </div>
</div>
</sec:authorize>
</div>