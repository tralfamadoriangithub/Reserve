<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link href="styles/style.css" rel="stylesheet" type="text/css" />
</head>
<section id="logo"></section>
<section id="headerSection" class="headerSectionStyle">
	<section id="loginButtons" class="loginButtonsStyle">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="register_command" /> <input
				type="submit" value="Register" />
		</form>
		<c:if test="${not session.login }">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="sign_in_command" /> <input
				type="submit" value="Sign In" />
		</form>
		</c:if>
		<c:if test="${ session.login }">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="sign_in_command" /> <input
				type="submit" value="Sign Out" />
		</form>
		</c:if>
	</section>
	<section id="languageSelect"></section>
</section>
</html>