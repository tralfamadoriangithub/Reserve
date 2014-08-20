<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="tabletag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />
<fmt:message var="title" key="operator"/>

<myTag:basepage title="${ title }">

	<h3 class = "tableLabel">
		<fmt:message key="claims" />
	</h3>
	<tabletag:operatorclaimstable claims="${ sessionScope.claims }" />
	<br>
	<h3 class = "tableLabel">
		<fmt:message key="assignations" />
	</h3>
	<tabletag:assignationstable
		assignations="${ sessionScope.assignations }" />
	<br>

</myTag:basepage>
