<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<link href="styles.css" rel="stylesheet" type="text/css"/>
</head>
<section id="logo"></section>
<section id="headerSection" class="headerSectionStyle">
	<section id="loginButtons">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="register_command" /> <input
				type="submit" value="Register" />
		</form>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="sign_in_command" /> <input
				type="submit" value="Sign In" />
		</form>
	</section>
	<section id="languageSelect"></section>
</section>
</html>