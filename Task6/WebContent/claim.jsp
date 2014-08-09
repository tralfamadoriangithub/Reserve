<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string"/>
<fmt:message var="title" key="label.claim"/>

<myTag:basepage title="${ title }">
	<section id="claimForm">
		<h3><fmt:message key="label.claim"/>:</h3>
		<br>
		<p><strong><fmt:message key="label.street"/>: </strong><c:out value="${ sessionScope.claimAddress.street }"/><br>
		<p><strong><fmt:message key="label.house"/>: </strong><c:out value="${ sessionScope.claimAddress.houseNumber }"/><br>
		<p><strong><fmt:message key="label.block"/>: </strong><c:out value="${ sessionScope.claimAddress.blockNumber}"/><br>
		<p><strong><fmt:message key="label.flat"/>: </strong><c:out value="${ sessionScope.claimAddress.flatNumber }"/><br> <br>
		<h5><fmt:message key="str.describe_problem"/>:</h5>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="send_claim_command"/>
			<input type="text" name="problem_description" value="" height="40px"/>
			<br>
			<input type="submit" value="Send claim"/>
		</form>
	</section>

</myTag:basepage>