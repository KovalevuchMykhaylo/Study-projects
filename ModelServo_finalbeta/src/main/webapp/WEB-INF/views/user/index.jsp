<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<label style=color:red><h3> To buy you need to register!!!</h3></label>
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
		<div class="col-md-7 col-xs-12 parent">
		<c:forEach items="${page.content}" var="modelServo">
			<div>
			<custom:hiddenInputs excludeParams="name, price"/>
				<img src="/images/modelServo/${modelServo.id}.jpg?version=${modelServo.version}" width="50%">
				<p>Модель: ${modelServo.name}</p>
				<p>Ціна: ${modelServo.price} грн </p>
				<p>Розмір: ${modelServo.size.size} мм</p>
				<p>Тип шестерні: ${modelServo.gearType.gearType} </p>
				<p>Робоча напруга: ${modelServo.powerVoltage.powerVoltage} </p>
				<p>Кут Повороту: ${modelServo.rotationAngle.angle} </p>
				<p>Вага: ${modelServo.forceShaft.forceShaft} кг </p>
				<p>Зусилля на валу: ${modelServo.weight.weight} г </p>
				<sec:authorize access="isAuthenticated()">
						<a href="/buy/${modelServo.id}" class="btn btn-primary">В корзину!</a>
				</sec:authorize>
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
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
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
<script type="text/javascript">
$("#myCarousel").carousel();
</script>