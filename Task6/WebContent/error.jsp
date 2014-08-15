<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string"/>
<fmt:message var="title" key="error"/>

<myTag:basepage title="${ title }">
	<section id="errorMessage">
		<h2>Ooops!...</h2>
		<br>
		<h3><fmt:message key="error_message"/> :(</h3>
		<br>
		<a href="/Task6">Home page</a>
	</section>
</myTag:basepage>