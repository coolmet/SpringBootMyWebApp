<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="login.title" /></title>
<meta charset="UTF-8">
<meta name="viewport" >
<!--===============================================================================================-->
<link rel="icon" type="image/png" href="/logines/images/icons/favicon.ico" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css" href="/logines/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="/logines/fonts/iconic/css/material-design-iconic-font.min.css">
<link rel="stylesheet" type="text/css" href="/logines/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="/logines/vendor/animsition/css/animsition.min.css">
<link rel="stylesheet" type="text/css" href="/logines/css/main.css">
<link rel="stylesheet" type="text/css" href="/logines/css/util.css">
<link rel="stylesheet" type="text/css" href="/particles/css/style.css" />
<link rel="stylesheet" type="text/css" href="/bootstrap-4.3.1/css/bootstrap.min.css" />
<!--===============================================================================================-->
</head>
<body>
	<script type="text/javascript">
		function selectLanguage(imagePath, localeName) {
			localStorage.setItem("locales", localeName);
			$('#dropdownmenu').val(imagePath);
			var selectedOption = $('#dropdownmenu').val();
			document.getElementById("langmainimage").src = imagePath;
			//window.location.replace('?lang=' + localeName);
			//history.pushState({}, null, window.location.href.split('?')[0]+ '?lang=' + localeName);
			history.replaceState({}, window.location.href, window.location.href
					.split('?')[0]
					+ '?lang=' + localeName);
			$.post(window.location.href.split('?')[0] + '?lang=' + localeName,
					function(data, status) {
					});

			$("#limiter").load(location.href + " #limiter", "");
		}
	</script>
	<form>
		<div style="position: relative">
			<div id="particles-js"></div>
			<div id="limiter" class="limiter">
				<div id="containerlogin100" class="container-login100">
					<div class="top-right" style="width: 100%; position: fixed; text-align: right; top: 5px; right: 5px;">
						<div class="btn-group dropleft">
							<button class="btn btn-primary btn-sm" aria-expanded="false" aria-haspopup="true" data-toggle="dropdown" type="button"
								style="background-color: transparent; border: none; padding: 1px; outline: none !important; box-shadow: none !important;"
							>
								<b class="caret"></b>
								<img class="img-thumbnail hidden-xs" src=${deflangimagepath } id="langmainimage"
									style="width: 30px; background-color: transparent; border: none; padding: 0px;"
								>
							</button>
							<ul class="dropdown-menu" id="dropdownmenu"
								style="background-color: transparent; text-align: right; top: 10px; min-width: 30px; padding: 0px; margin-top: 10px;"
							>
								<c:forEach items="${languages}" var="language" varStatus="status">
									<img class="img-thumbnail hidden-xs" src=${language.imagePath }
										style="width: 30px; background-color: transparent; border: none; padding: 0px;"
										onclick="selectLanguage('${language.imagePath}','${language.localeName}')"
									>
									<c:if test="${not status.last}">
										<li class="dropdown-divider" style="margin: .2rem 0;"></li>
									</c:if>
								</c:forEach>
							</ul>
						</div>
					</div>
					<div id="wraplogin100" class="wrap-login100">
						<div class="login100-form validate-form">
							<span class="login100-form-title p-b-26">
								<spring:message code="login.header" />
							</span>
							<span class="login100-form-title p-b-48">
								<i class="zmdi zmdi-accounts"></i>
							</span>
							<!-- zmdi-account-o 						
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
							<div class="wrap-input100 validate-input" data-validate="<spring:message code="login.enterusername" />">
								<input class="input100" type="text" name="username" id="username">
								<span class="focus-input100" data-placeholder="<spring:message code="login.username" />"></span>
							</div>
							<div class="wrap-input100 validate-input" data-validate="<spring:message code="login.enterpassword" />">
								<span class="btn-show-pass">
									<i class="zmdi zmdi-eye"></i>
								</span>
								<input class="input100" type="password" name="pass" id="pass">
								<span class="focus-input100" data-placeholder="<spring:message code="login.password" />"></span>
							</div>
							<div class="wrap-input110">
								<input id="rememberme" name="remember-me" type="checkbox">
								<label for="rememberme">
									<spring:message code="login.rememberme" />
								</label>
							</div>
							<div class="container-login100-form-btn">
								<div class="wrap-login100-form-btn">
									<div class="login100-form-bgbtn"></div>
									<button class="login100-form-btn" id="registerBtn" onclick="loginBtn()">
										<spring:message code="login.login" />
									</button>
								</div>
							</div>
							${status}
							<div class="wrap-input110">
								<a href="testloginregister">
									<spring:message code="login.registernow" />
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<!--===============================================================================================-->
	<script src="/logines/vendor/jquery/jquery-3.2.1.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/vendor/bootstrap/js/popper.min.js"></script>
	<!--===============================================================================================-->
	<script src="/logines/js/main.js"></script>
	<!--===============================================================================================-->
	<script src="/particles/js/particles.js"></script>
	<script src="/particles/js/particles.min.js"></script>
	<script src="/particles/js/app.js"></script>
	<!--===============================================================================================-->
	<script src="/bootstrap-4.3.1/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
</body>
</html>