<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
</head>
<body>
<h2>Welcome: ${user.name}</h2>
<h2><img src="/images/users/${user.id}.jpg?version=${user.version}" width="100%"></h2>
<%-- <h2>Your rent: ${user.rentDates}</h2> --%>
	<p>Run forest run</p>
	<c:forEach items= "${rentDates}" var="rentDate">
	<p>${rentDate.first} - ${rentDate.second}</p>
	</c:forEach>
</body>
</html>