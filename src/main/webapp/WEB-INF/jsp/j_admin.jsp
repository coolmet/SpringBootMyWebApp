<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<style>
table, th, td {
	border: 1px solid black;
}
​
</style>

	<div style="float: left; padding-left: 10px" >
		<table>
			<thead>
				<tr style="font-weight: bold;" bgcolor="lightblue">
					<td>Link</td>
					<td>Path</td>
					<td>Page</td>
				</tr>
			</thead>
			<tr>
				<td><a href="/admin/actuator">/admin/actuator</a></td>
				<td>/admin/actuator</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/info">/admin/actuator/info</a></td>
				<td>/admin/actuator/info</td>
				<td></td>
			</tr>
			</tr>
			<tr>
				<td><a href="/admin/actuator/auditevents">/admin/actuator/auditevents</a></td>
				<td>/admin/actuator/auditevents</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/caches">/admin/actuator/caches</a></td>
				<td>/admin/actuator/caches</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/conditions">/admin/actuator/conditions</a></td>
				<td>/admin/actuator/conditions</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/configprops">/admin/actuator/configprops</a></td>
				<td>/admin/actuator/configprops</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/httptrace">/admin/actuator/httptrace</a></td>
				<td>/admin/actuator/httptrace</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/integrationgraph">/admin/actuator/integrationgraph</a></td>
				<td>/admin/actuator/integrationgraph</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/scheduledtasks">/admin/actuator/scheduledtasks</a></td>
				<td>/admin/actuator/scheduledtasks</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/sessions">/admin/actuator/sessions</a></td>
				<td>/admin/actuator/sessions</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/shutdown">/admin/actuator/shutdown</a></td>
				<td>/admin/actuator/shutdown</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/health">/admin/actuator/health</a></td>
				<td>/admin/actuator/health</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/loggers">/admin/actuator/loggers</a></td>
				<td>/admin/actuator/loggers</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/beans">/admin/actuator/beans</a></td>
				<td>/admin/actuator/beans</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/metrics">/admin/actuator/metrics</a></td>
				<td>/admin/actuator/metrics</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/mappings">/admin/actuator/mappings</a></td>
				<td>/admin/actuator/mappings</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/env">/admin/actuator/env</a></td>
				<td>/admin/actuator/env</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/threaddump">/admin/actuator/threaddump</a></td>
				<td>/admin/actuator/threaddump</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/heapdump">/admin/actuator/heapdump</a></td>
				<td>/admin/actuator/heapdump</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/logfile">/admin/actuator/logfile</a></td>
				<td>/admin/actuator/logfile</td>
				<td></td>
			</tr>
			<tr>
				<td><a href="/admin/actuator/trace">/admin/actuator/trace</a></td>
				<td>/admin/actuator/trace</td>
				<td></td>
			</tr>
		</table>
	</div>
	
	<!-- ################################## -->
	<div style="float: left; padding-left: 10px" >
		<table>
			<thead>
				<tr style="font-weight: bold;" bgcolor="lightblue">
					<td>Link</td>
					<td>Path</td>
					<td>Page</td>
				</tr>
			</thead>
			<tr>
				<td><a href="/admin">/admin</a></td>
				<td>/admin</td>
				<td>/admin</td>
			</tr>
			<tr>
				<td><a href="/admin/welcome">/admin/welcome</a></td>
				<td>/admin/welcome</td>
				<td>@ResponseBody</td>
			</tr>
			<tr>
				<td><a href="/">/</a></td>
				<td>/</td>
				<td>j_index.jsp</td>
			</tr>
			<tr>
				<td><a href="/index">/index</a></td>
				<td>/index</td>
				<td>j_index.jsp</td>
			</tr>
			<tr>
				<td><a href="/login">/login</a></td>
				<td>/login</td>
				<td>j_login.jsp</td>
			</tr>
			<tr>
				<td><a href="/admin/users">/admin/users</a></td>
				<td>/admin/users</td>
				<td>j_users.jsp</td>
			</tr>
		</table>
	</div>

</body>
</html>