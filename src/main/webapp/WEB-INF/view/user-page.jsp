<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>User</title>
</head>
<body>
      <h1>User Page</h1>
      <form:form action="logout" method="POST">
      	<input type="submit" value="logout" />
      </form:form>
</body>
</html>