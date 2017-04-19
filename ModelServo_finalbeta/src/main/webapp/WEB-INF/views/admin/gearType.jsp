<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>

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
					<li><a href="/admin/modelServo">ModelServo</a></li>
					<li><a href="/admin/forceShaft">ForceShaft</a></li>
					<li><a href="/admin/gearType">GearType</a></li>
					<li><a href="/admin/powerVoltage">PowerVoltage</a></li>
					<li><a href="/admin/rotationAngle">RotationAngle</a></li>
					<li><a href="/admin/size">Size</a></li>
					<li><a href="/admin/speedRotation">SpeedRotation</a></li>
					<li><a href="/admin/type">Type</a></li>
					<li><a href="/admin/weight">Weight</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
<%-- 		<form:form class="form-inline" action="/admin/gearType" method="GET" modelAttribute="filter"> --%>
<%-- 			<custom:hiddenInputs excludeParams="search"/> --%>
<!-- 			<div class="form-group"> -->
<%-- 				<form:input path="search" class="form-control" placeholder="Search"/> --%>
<!-- 			</div> -->
<!-- 			<button gearType="submit" class="btn btn-primary">Ok</button> -->
<!-- 			<a href  = "/admin/weight/cancel" class="btn btn-primary" >Cancel</a> -->
<%-- 		</form:form> --%>
	</div>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/gearType" method="POST" modelAttribute="gearType">
					<div class="form-group">
						<label for="gearType" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="gearType"/></label>
					</div>
					<div class="form-group">
    					<label for="gearType" class="col-sm-2 control-label">GearType</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="gearType" id="gearType"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-primary">Create</button>
      						<a href  = "/admin/gearType/cancel" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>GearType</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Update</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${gearTypes}" var="gearType">
				<div class="row">
					<div class="col-md-4 col-xs-4">${gearType.gearType}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-success" href="/admin/gearType/update/${gearType.id}">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/gearType/delete/${gearType.id}">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
<!-- 		<div class="row"> -->
<!-- 					<div class="col-md-6 col-xs-6 text-center"> -->
<!-- 						<div class="dropdown"> -->
<!-- 							<button class="btn btn-primary dropdown-toggle" gearType="button" -->
<!-- 								data-toggle="dropdown"> -->
<!-- 								Sort <span class="caret"></span> -->
<!-- 							</button> -->
<!-- 							<ul class="dropdown-menu"> -->
<%-- 								<custom:sort innerHtml="weight asc" paramValue="weight" /> --%>
<%-- 								<custom:sort innerHtml="weight desc" paramValue="weight,desc" /> --%>
<!-- 							</ul> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 					<div class="col-md-6 col-xs-6 text-center"> -->
<%-- 						<custom:size posibleSizes="1,2,5,10" size="${page.size}" /> --%>
<!-- 					</div> -->
<!-- 				</div> -->
	</div>
</div>
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>