<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
</head>
<body>

	<c:if test="${param.error != null}">
		<i style="color:red;">Invalid login & password</i>
	</c:if>
	
	<c:if test="${param.logout != null}">
		<i style="color:red;">Logout successfully</i>
	</c:if>
		
	<h1>My Custom Login</h1>
    <form:form>
        Username : <input  name="username" type="text" /><br />
        Password : <input name="password" type="password" /><br />
        <button name="submit">Login</button>
    </form:form>
</body>
</html>