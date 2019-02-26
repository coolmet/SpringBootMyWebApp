<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>J/Index</title>
</head>
<body>
	<h1>J-Index Page</h1>
	<form action="logout" method="post">
		<input type="submit" value="Logout"> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}">
	</form>
</body>
</html>