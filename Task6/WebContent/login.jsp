<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="label.login"/></title>
</head>

<myTag:basepage>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="get_user_page_command" />
		<p>
			<fmt:message key="label.login"/>:<br> <input type="text" placeholder="Login" name="login"
				value="" required />
		<p>
			<fmt:message key="label.password"/>:<br> <input type="password" placeholder="Password"
				name="password" value="" required /> <br> <input type="submit"
				value="Login" />
	</form>
</myTag:basepage>