<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Signup</title>
</head>
<body>
	<h1>Sign up here !</h1>
	<form:form action="process-signup" method="POST" modelAttribute="signupdto">
		Username : <form:input path="username" /><br>
		Password : <form:password path="password" /><br>
		<input type="submit" value="signup">
	</form:form>
</body>
</html>