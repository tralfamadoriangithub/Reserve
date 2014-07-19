<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:useBean id="user" class="com.epam.task6.entity.User"
	scope="request"></jsp:useBean>
<jsp:setProperty property="*" name="user" />
<title>USER <jsp:getProperty property="name" name="user" /></title>
</head>
<body>
	<c:import url="header.jsp"/>
	<h1>user</h1>
	
</body>
</html>