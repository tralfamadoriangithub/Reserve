<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<section>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="get_user_page_command"/>
			<p>Login:<br>
			<input type="text" placeholder="ULogin" name="login" value="" required/>
			<p>Password:<br>
			<input type="password" placeholder="Password" name="password" value="" required/>
			<br>
			<input type="submit" value="Login"/>
		</form>
	</section>
</body>
</html>