<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>J/Login</title>
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/bootstrap-4.3.1/css/bootstrap.min.css" />
<!--===============================================================================================-->
</head>
<body>
	<h1>J-Login Page</h1>
	<form action="J" method="post">
		Username:
		<input name="username" type="text" />
		<br />
		Password:
		<input name="password" type="password" />
		<br />
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		Remember Me:
		<input name="remember-me" type="checkbox">
		<br />
		<input type="submit" value="Login">
	</form>
	<c:if test="${not empty param.loginFailed}">
		<div class="alert alert-danger" role="alert">
			Login Failed!!!
			<br />
			Reason :

			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
			</c:if>
		</div>
	</c:if>
</body>
</html>