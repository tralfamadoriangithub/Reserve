<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="tabletag"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string"/>
<fmt:message var="title" key="label.administrator"/>

<myTag:basepage title="${ title }">
	<c:choose>
		<c:when test="${ empty sessionScope.user }">
			<label>Root admin page</label>
		</c:when>
		<c:otherwise>
			<label>Admin page</label>
		</c:otherwise>
	</c:choose>

	<br>
	<form action="controller" method="post">
	<input type="hidden" name="command" value="delete_database_command"/>
		<input type="submit" value="Delete DB" />
	</form>
	<br>
	<form action="controller" method="post">
	<input type="hidden" name="command" value="create_database_command"/>
		<input type="submit" value="Create DB" />
	</form>

</myTag:basepage>