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


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="label.operator" /></title>
</head>
<myTag:basepage>

	<h3>
		<fmt:message key="label.claims" />
	</h3>
	<tabletag:operatorclaimstable claims="${ sessionScope.claims }" />
	<br>
	<h3>
		<fmt:message key="label.assignations" />
	</h3>
	<tabletag:assignationstable
		assignations="${ sessionScope.assignations }" />
	<br>
	<h3>
		<fmt:message key="label.workers" />
	</h3>
	<tabletag:workerstable workers="${ sessionScope.workers }" />

</myTag:basepage>
