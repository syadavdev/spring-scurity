<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home Page</title>
</head>
<body>
      <h1>Hi ${username}</h1>
      <h4>Roles Assigned : ${roles}</h4>
      
      <sec:authorize access="hasAuthority('USER')">
      	<a href="user">Show User Screen</a><br>
      </sec:authorize>
      <sec:authorize access="hasAuthority('ADMIN')">
      	<a href="admin">Show Admin Screen</a>
      </sec:authorize>
      
      <form:form action="logout" method="POST">
      	<input type="submit" value="logout" />
      </form:form>
</body>
</html>