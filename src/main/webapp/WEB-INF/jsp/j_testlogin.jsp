<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>J/Giriş</title>
<meta charset="UTF-8">
<meta name="viewport" >
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="/logines/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/css/bootstrap.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/fonts/iconic/css/material-design-iconic-font.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/css/animate.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/css/hamburgers.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/css/animsition.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/css/select2.min.css">
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/css/daterangepicker.css">
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
						<a href="testloginregister">Üye Ol</a>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div id="dropDownSelect1"></div>
	<!--===============================================================================================-->
	<script src="/logines/js/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/popper.js"></script>
	<script src="/logines/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/select2.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/moment.min.js"></script>
	<script src="/logines/js/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/main.js"></script>
	<!--===============================================================================================-->

</body>
</html>