<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Giriş</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="/logines/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/vendor/animate/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/vendor/select2/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/css/util.css">
<link rel="stylesheet" type="text/css" href="/logines/css/main.css">
<!--===============================================================================================-->

</head>
<body>
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-form validate-form">
					<span class="login100-form-title p-b-20"> Login </span> <span class="login100-form-title p-b-30"> <i class="zmdi zmdi-accounts"></i> <!-- zmdi-account-o 						
						zmdi-face 
						zmdi-accounts-list-alt 
						zmdi-shield-security 
						zmdi-accounts 
						zmdi-accounts-add
						zmdi-account
						zmdi-account-add
						zmdi-badge-check
						zmdi-account-box-o
						-->
					</span>

					<div class="wrap-input100 validate-input" data-validate="Kullanıcı Adı Girin">
						<input class="input100" type="text" name="username" id="username"> <span class="focus-input100" data-placeholder="Kullanıcı Adı"></span>
					</div>

					<div class="wrap-input100 validate-input" data-validate="Parola Girin">
						<span class="btn-show-pass"> <i class="zmdi zmdi-eye"></i>
						</span> <input class="input100" type="password" name="pass" id="pass"> <span class="focus-input100" data-placeholder="Parola"></span>
					</div>


					<div class="container-login100-form-btn">
						<div class="wrap-login100-form-btn">
							<div class="login100-form-bgbtn"></div>
							<button class="login100-form-btn" id="registerBtn" onclick="loginBtn()">Giriş</button>
						</div>
					</div>

					${status}
					<div class="text-center p-t-115">
						<a href="register">Üye Ol</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>
	<!--===============================================================================================-->
	<script src="/logines/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/vendor/bootstrap/js/popper.js"></script>
	<script src="/logines/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/vendor/daterangepicker/moment.min.js"></script>
	<script src="/logines/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/main.js"></script>
	<!--===============================================================================================-->

</body>
</html>