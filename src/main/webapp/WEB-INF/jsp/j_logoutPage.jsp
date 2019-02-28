<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>J/Logout</title>
</head>
<body>
	<h1>J-Logout Page</h1>
	<form action="logout" method="post">
		<input type="submit" value="Logout"> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
</body>
</html>