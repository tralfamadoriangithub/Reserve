<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<c:set var="language"
	value="${not empty seccionScope.language ? seccionScope.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />
<fmt:message key="login" var="title"/>

<myTag:basepage title="${ title }">
	<section id="loginForm">	
		<form action="controller" method="post">
		
			
			
			<section class=inputFieldWithTitle>	
				<p><fmt:message key="login" />:
				<br> <input type="text" placeholder='<fmt:message key="login" />' name="login" 
					value="" required />
			</section>
			<p>
				<fmt:message key="password" />
				:<br> <input type="password" placeholder='<fmt:message key="password" />'
					name="password" value="" required /> <br> 
					<br> <input
					type="submit" value='<fmt:message key="sign_in"/>' />
					<input type="hidden" name="command" value="get_user_page_command" />			
		</form>
		<br>
		<h3 style="color: rgba(255, 0, 0, 0.6)"><c:out value="${ requestScope.message }"/></h3>
	</section>
</myTag:basepage>