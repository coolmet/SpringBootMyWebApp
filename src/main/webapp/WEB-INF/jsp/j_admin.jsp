<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>J/Admin</title>
<!--===============================================================================================-->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/bootstrap-4.3.1/css/bootstrap-essentials.min.css" />
<!--===============================================================================================-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="/bootstrap-4.3.1/js/bootstrap-essentials.min.js"></script>
<!--===============================================================================================-->

</head>
<body>
	<style>
table, th, td {
	border: 1px solid black;
}

.dropdown-submenu {
	position: relative;
}

.dropdown, .dropleft, .dropright, .dropup {
	position: relative;
	padding-left: 5px;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: 10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #555;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}

.dropdown-item {
	font-weight: 400;
	font-size: 14px;
	padding: 2px;
	font-size: 14px;
}

.scrollable-menu {
	height: auto;
	max-height: 50vh;
	overflow-x: hidden;
}

span.tab-1 {
	word-spacing: 5em;
}

span.tab-2 {
	word-spacing: 5em;
}
</style>


	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- <nav class="navbar navbar-expand-sm navbar-light" style="background-color: #e3f2fd;">-->
		<ul class="navbar-nav">

			<!-- ################################## -->

			<li class="nav-item dropdown">
				<a class="nav-link btn btn-outline-info mr-2 dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> Actuator </a>
				<div class="dropdown-menu scrollable-menu" role="menu">
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator">Actuator | /admin/actuator</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/auditevents">Auditevents | /admin/actuator/auditevents</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/beans">Beans | /admin/actuator/beans</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/caches">Caches | /admin/actuator/caches</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/conditions">Conditions | /admin/actuator/conditions</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/configprops">Configprops | /admin/actuator/configprops</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/env">Env | /admin/actuator/env</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/health">Health | /admin/actuator/health</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/heapdump">Heapdump | /admin/actuator/heapdump</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/httptrace">Httptrace | /admin/actuator/httptrace</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/info">Info | /admin/actuator/info</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/integrationgraph">Integrationgraph | /admin/actuator/integrationgraph</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/logfile">Logfile | /admin/actuator/logfile</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/loggers">Loggers | /admin/actuator/loggers</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/mappings">Mappings | /admin/actuator/mappings</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/metrics">Metrics | /admin/actuator/metrics</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/scheduledtasks">Scheduledtasks | /admin/actuator/scheduledtasks</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/sessions">Sessions | /admin/actuator/sessions</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/shutdown">Shutdown | /admin/actuator/shutdown</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/threaddump">Threaddump | /admin/actuator/threaddump</a>
					<a class="dropdown-item" tabindex="-1" href="/admin/actuator/trace">Trace | /admin/actuator/trace</a>
				</div>
			</li>

			<!-- ################################## -->

			<li class="nav-item dropdown">
				<a class="nav-link btn btn-outline-danger mr-2 dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> Annoymus </a>
				<div class="dropdown-menu" role="menu">
					<div class="dropdown-submenu">
						<a class="dropdown-item" tabindex="-1" href="#">Th</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" tabindex="-1" href="/th">/th | th_index.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/index/th">/index/th | th_index.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/login/th">/login/th | th_login.jsp</a>
						</div>
					</div>
					<div class="dropdown-divider"></div>
					<div class="dropdown-submenu">
						<a class="dropdown-item" tabindex="-1" href="#">JSP</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" tabindex="-1" href="/">/ | j_index.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/index">/index | j_index.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/login">/login | j_login.jsp</a>

						</div>
					</div>
				</div>
			</li>

			<!-- ################################## -->

			<li class="nav-item dropdown">
				<a class="nav-link btn btn-outline-warning mr-2 dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> Admin </a>
				<div class="dropdown-menu " role="menu">
					<a class="dropdown-item" tabindex="-1" href="/admin/welcome">/admin/welcome | @ResponseBody</a>
					<div class="dropdown-divider"></div>
					<div class="dropdown-submenu">
						<a class="dropdown-item" tabindex="-1" href="#">Th</a>
						<div class="dropdown-menu">
							<a class="dropdown-item" tabindex="-1" href="/admin/th">/admin/th | th_admin.html</a>
							<a class="dropdown-item" tabindex="-1" href="/admin/th/users">/admin/th/users | th_users.html</a>
						</div>
					</div>
					<div class="dropdown-divider"></div>
					<div class="dropdown-submenu">
						<a class="dropdown-item" tabindex="-1" href="#">JSP</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" tabindex="-1" href="/admin">/admin | j_admin.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/admin/users">/admin/users | j_users.jsp</a>

						</div>
					</div>
				</div>
			</li>

			<!-- ################################## -->

			<li class="nav-item dropdown ">
				<a class="nav-link btn btn-outline-primary mr-2 dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> Test </a>
				<div class="dropdown-menu" role="menu">
					<div class="dropdown-submenu">
						<a class="dropdown-item" tabindex="-1" href="#">Th</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" tabindex="-1" href="#">User 3.1</a>
							<a class="dropdown-item" tabindex="-1" href="#">User 3.2</a>
						</div>
					</div>
					<div class="dropdown-divider"></div>
					<div class="dropdown-submenu">
						<a class="dropdown-item" tabindex="-1" href="#">JSP</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" tabindex="-1" href="/admin/testloginparticles">/admin/testloginparticles | j_testloginparticles.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/admin/testloginregister">/admin/testloginregister | j_testloginregister.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/admin/testlogin">/admin/testlogin | j_testlogin.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/admin/testparticles">/admin/testparticles | j_testparticles.jsp</a>
							<a class="dropdown-item" tabindex="-1" href="/admin/testlocale"> /admin/testlocale | j_testlocale.jsp </a>
						</div>
					</div>
				</div>
			</li>

			<!-- ################################## -->

			<!-- invisible-->
			<!-- <span class="tab-1" />-->
			<li class="nav-item dropdown ">
				<a class="nav-link btn btn-outline-success mr-2 dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown"> USER </a>
				<div class="dropdown-menu" role="menu">
					<a class="dropdown-item" tabindex="-1" href="#">User 1</a>
					<a class="dropdown-item" tabindex="-1" href="#">User 2</a>
					<div class="dropdown-submenu">
						<a class="dropdown-item" tabindex="-1" href="#">User 3</a>
						<div class="dropdown-menu" role="menu">
							<a class="dropdown-item" tabindex="-1" href="#">User 3.1</a>
							<a class="dropdown-item" tabindex="-1" href="#">User 3.2</a>
						</div>
					</div>
				</div>
			</li>


		</ul>
	</nav>


	<!-- ################################## -->

	<div style="float: left; padding-left: 10px">
		<table>
			<thead>
				<tr style="font-weight: bold;" bgcolor="lightblue">
					<td>Link</td>
					<td>Path</td>
					<td>Page</td>
				</tr>
			</thead>
			<tr>
				<td><a href="/admin/th/users">/admin/th/users</a></td>
				<td>admin/th/users</td>
				<td>th_users.html</td>
			</tr>
		</table>
	</div>
</body>
</html>