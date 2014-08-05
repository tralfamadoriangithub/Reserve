<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string"/>
<fmt:message var="register" key="label.registration"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${ register }</title>
</head> 
<myTag:basepage>
	<section>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="register_new_user_command"/>
			<p><fmt:message key="label.name"/>:<br>
			<input type="text" placeholder="<fmt:message key="label.name"/>" name="name" required/>
			<p><fmt:message key="label.surname"/>:<br>
			<input type="text" placeholder="<fmt:message key="label.surname"/>" name="surname" required/>
			<p><fmt:message key="label.phone"/>:<br>
			<input type="text" placeholder="<fmt:message key="label.phone"/>" name="phone" required/>
			<p><fmt:message key="label.login"/>:<br>
			<input type="text" placeholder="<fmt:message key="label.login"/>" name="login" required/>
			<p><fmt:message key="label.password"/>:<br>
			<input type="password" placeholder="<fmt:message key="label.password"/>" name="password" required/>
			<p><fmt:message key="label.password_confirm"/>:<br>
			<input type="password" placeholder="<fmt:message key="label.password_confirm"/>" name="confirm_password" required/>
			<br>
			<input type="submit" value="${ register }"/>
		</form>
	</section>
</myTag:basepage>