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
		<form:form class="form-inline" action="/admin/modelServo" method="GET" modelAttribute="filter">
			<custom:hiddenInputs excludeParams="min, max, forceShaft, poverVoltage, gearType, rotationAngle, speedRotation, search,
			 _forceShaftIds, _poverVoltageIds, _gearTypeIds, _rotationAngleIds, _speedRotationIds"/>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max"/>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-6">
					<form:input path="search" class="form-control" placeholder="search"/>
				</div>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Ok</button>
				<a href  = "/admin/modelServo/cancel" class="btn btn-primary" >Cancel</a>
			</div>
		</form:form>
	</div>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12"></div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/modelServo" method="POST" modelAttribute="modelServo" enctype="multipart/form-data">
					<label for="modelServo" style="color:red;text-align:left;" class="col-sm-10 col-sm-offset-2 control-label"><form:errors path="*"/></label>
					<div class="form-group">
    					<label for="modelServo" class="col-sm-2 control-label">Model Servo</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="name" id="modelServo"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="modelServo" class="col-sm-2 control-label">Price of Model Servo</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="price" id="modelServo"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="forceShaft" class="col-sm-2 control-label">Force Shaft</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="forceShaft" id="forceShaft"  items="${forceShafts}" itemValue="id" itemLabel="forceShaft"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="gearType" class="col-sm-2 control-label">Gear Type</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="gearType" id="gearType"  items="${gearTypes}" itemValue="id" itemLabel="gearType"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="powerVoltage" class="col-sm-2 control-label">Power Voltage</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="powerVoltage" id="powerVoltage"  items="${powerVoltages}" itemValue="id" itemLabel="powerVoltage"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="rotationAngle" class="col-sm-2 control-label">Rotation Angle</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="rotationAngle" id="rotationAngle"  items="${rotationAngles}" itemValue="id" itemLabel="angle"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="size" class="col-sm-2 control-label">Size</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="size" id="size" items="${sizes}" itemValue="id" itemLabel="size"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="speedRotation" class="col-sm-2 control-label">Speed Rotation</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="speedRotation" id="speedRotation" items="${speedRotations}" itemValue="id" itemLabel="speedRotation"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="type" class="col-sm-2 control-label">Type</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="type" id="type" items="${types}" itemValue="id" itemLabel="type"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="weight" class="col-sm-2 control-label">Weight</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="weight" id="weight" items="${weights}" itemValue="id" itemLabel="weight"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="file" class="col-sm-2 control-label">Image</label>
    					<div class="col-sm-10">
      						<input name="file" id="file" type="file">
    					</div>
					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-primary">Create</button>
      						<a href  = "/admin/modelServo/cancel" class="btn btn-primary" >Cancel</a>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>ModelServo Name</h3></div>
<!-- 			<div class="col-md-2 col-xs-2"><h3>ForceShaft</h3></div> -->
<!-- 			<div class="col-md-2 col-xs-2"><h3>Size</h3></div> -->
<!-- 			<div class="col-md-2 col-xs-2"><h3>Update</h3></div> -->
<!-- 			<div class="col-md-2 col-xs-2"><h3>Delete</h3></div> -->
		</div>
			<c:forEach items="${page.content}" var="modelServo">
				<div class="row">
					<div class="col-md-4 col-xs-4">${modelServo.name}</div>
					<div class="col-md-2 col-xs-2">${modelServo.price}</div>
<%-- 					<div class="col-md-2 col-xs-2">${modelServo.size.size}</div> --%>
					<div class="col-md-2 col-xs-2"><a class="btn btn-success" href="/admin/modelServo/update/${modelServo.id}">update</a></div>
					<div class="col-md-2 col-xs-2"><a class="btn btn-danger" href="/admin/modelServo/delete/${modelServo.id}">delete</a></div>
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
								<custom:sort innerHtml="modelServo asc" paramValue="modelServo" />
								<custom:sort innerHtml="modelServo desc" paramValue="modelServo,desc" />
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