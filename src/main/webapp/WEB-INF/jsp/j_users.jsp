<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>J/Users</title>
</head>
<body>
	<style>
table, th, td {
	border: 1px solid black;
}
​
</style>
	<div style="float: top; padding-left: 10px">
		<table>
			<thead>
				<tr style="font-weight: bold;" bgcolor="lightblue">
					<td>ID</td>
				</tr>
			</thead>
			<c:forEach items="${usersString}" var="user" varStatus="status">
				<tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
					<td>${user}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- ################################## -->
	<div style="float: left; padding-left: 10px">

		<table>
			<thead>
				<tr style="font-weight: bold;" bgcolor="lightblue">
					<td>Username</td>
					<td>Password</td>
					<td>Enabled</td>
					<td>AccountNonExpired</td>
					<td>credentialsNonExpired</td>
					<td>AccountNonLocked</td>
					<td>Granted Authorities</td>
				</tr>
			</thead>
			<c:forEach items="${users}" var="user" varStatus="status">
				<tr bgcolor=${status.index % 2 == 0?'white':'lightgray'}>
					<td>${user.getUsername()}</td>
					<td>${user.getPassword()}</td>
					<td>${user.isEnabled()}</td>
					<td>${user.isAccountNonExpired()}</td>
					<td>${user.isCredentialsNonExpired()}</td>
					<td>${user.isAccountNonLocked()}</td>
					<td>${user.getAuthorities().toString()}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<!-- ################################## -->
	<div style="float: left; padding-left: 10px">
		<table>
			<thead>
				<tr style="font-weight: bold;" bgcolor="lightblue">
					<td>Username</td>
					<td>Password</td>
					<td>Enabled</td>
					<td>AccountNonExpired</td>
					<td>credentialsNonExpired</td>
					<td>AccountNonLocked</td>
					<td>Granted Authorities</td>
				</tr>
			</thead>
			<tr>
				<td>${userDetail.getUsername()}</td>
				<td>${userDetail.getPassword()}</td>
				<td>${userDetail.isEnabled()}</td>
				<td>${userDetail.isAccountNonExpired()}</td>
				<td>${userDetail.isCredentialsNonExpired()}</td>
				<td>${userDetail.isAccountNonLocked()}</td>
				<td>${userDetail.getAuthorities().toString()}</td>
			</tr>
		</table>
	</div>
</body>
</html>