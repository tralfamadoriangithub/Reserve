<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="table"%>

<c:set var="language"
	value="${ not empty param.language ? param.language : not empty language ? language : pageContext.request.locale }"
	scope="session" />
<fmt:setLocale value="${ language }" />
<fmt:setBundle basename="string" />

<c:if test="${ not sessionScope.is_login }">
	<c:redirect url="login.jsp"></c:redirect>
</c:if>

<myTag:basepage title="${ sessionScope.user.name }">
	<h3 class = "tableLabel"><fmt:message key="addresses"/></h3>
	<table:addressestable addresses="${ sessionScope.addresses }" />
	<section class="tableButton">
		<a href="new_address.jsp" id="newAddressButton"><fmt:message key="add_address"/></a>
	</section>
	
	<br>
	<br>
	<h3 class = "tableLabel"><fmt:message key="claims"/></h3>
	<table:userclaimstable claims="${ sessionScope.claims }" />

</myTag:basepage>
