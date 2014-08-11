<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />
<fmt:message key="label.login" var="title"/>

<myTag:basepage title="${ title }">
	<section id="loginForm">
	
		<form action="controller" method="post">
			<input type="hidden" name="command" value="get_user_page_command" />
			<p>
				<fmt:message key="label.login" />
				:<br> <input type="text" placeholder="Login" name="login"
					value="" required />
			<p>
				<fmt:message key="label.password" />
				:<br> <input type="password" placeholder="Password"
					name="password" value="" required /> <br> <br> <input
					type="submit" value='<fmt:message key="button.sign_in"/>' />			
		</form>
		<br>
		<c:out value="${ requestScope.message }"/>
	</section>
</myTag:basepage>