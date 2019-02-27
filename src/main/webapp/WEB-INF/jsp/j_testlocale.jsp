<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><spring:message code="test.locale.msg" /></title>
<!--===============================================================================================-->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		var selItem = localStorage.getItem("locales");
		$('#locales').val(selItem ? selItem : 'en');
		$("#locales").change(function() {
			var selectedOption = $('#locales').val();
			if (selectedOption) {
				window.location.replace('?lang=' + selectedOption);
				localStorage.setItem("locales", selectedOption);
			}
		});
	});
</script>
<!--===============================================================================================-->
<style>
legend {
	width: auto;
	margin-left: auto;
	margin-right: auto;
}
</style>
<!--===============================================================================================-->
</head>

<body>
	<div style="width: 600px; margin: auto;">
		<fieldset>
			<legend>
				<spring:message code="test.locale.msg" />
			</legend>
			<p>
				<label>
					<spring:message code="test.locale.chooseLang" />
				</label>
				<select id="locales">
					<c:forEach items="${languages}" var="language" varStatus="status">
						<option value=${language.localeName}>${language.displayName}</option>
					</c:forEach>

				</select>
			</p>
		</fieldset>
		<div style="clear: both"></div>
		<div>
			<spring:message code="test.locale.copyright" />
			©
			<spring:message code="test.locale.year" />

		</div>
	</div>
</body>