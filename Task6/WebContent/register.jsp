<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<header>
		<c:import url="header.html"></c:import>
	</header>
	<section>
		<form action="controller" method="post">
			<p>User Name:<br>
			<input type="text" placeholder="User Name" name="user_name"/>
			<p>Password:<br>
			<input type="password" placeholder="Password" name="password"/>
			<p>Confirm password:<br>
			<input type="password" placeholder="Confirm Password" name="confirm_password"/>
			<br>
			<input type="submit" value="Submit"/>
		</form>
	</section>
</body>
</html>