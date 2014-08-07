<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="tabletag"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assignation</title>
</head>
<myTag:basepage>
	

</myTag:basepage>
