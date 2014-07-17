<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles/style.css" rel="stylesheet" type="text/css"/>
<title>Login</title>
</head>
	<section class="main_wrapper">
		<form action="controller" method="post">
			<input type="hidden" name="command" value="get_user_page_command"/>
			<p>Login:<br>
			<input type="text" placeholder="Login" name="login" value="" required/>
			<p>Password:<br>
			<input type="password" placeholder="Password" name="password" value="" required/>
			<br>
			<input type="submit" value="Login"/>
		</form>
	</section>
</html>