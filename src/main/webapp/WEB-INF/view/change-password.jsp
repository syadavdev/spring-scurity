<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Change Password</title>
</head>
<body>
<div align="center">

	  <c:if test="${param.notMatched != null}">
	  	<i style="color: red;">Confirm password not matching</i>
	  </c:if>
	  
	  <c:if test="${param.invalidPassword != null}">
	  	<i style="color: red;">Invalid old password</i>
	  </c:if>
      <h1>Change Password</h1>
      
      <form:form action="processPassword" method="POST" modelAttribute="change-pwd">
      
      	<label>Old Password : </label>
      	<form:input path="oldPassword" />
      	<br/>
      	
      	<label>New Password : </label>
      	<form:input path="newPassword" />
      	<br/>
      	
      	<label>Confirm Password : </label>
      	<form:input path="confirmPassword" />
      	
      	<br/>
      	<input type="submit" value="Change Password">
      </form:form>
</div>
</body>
</html>