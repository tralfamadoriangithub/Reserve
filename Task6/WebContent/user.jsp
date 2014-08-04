<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="table"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:useBean id="user" class="com.epam.task6.entity.User"
	scope="session"></jsp:useBean>
<jsp:setProperty property="*" name="user" />

<title><jsp:getProperty property="name" name="user" /> page</title>
</head>
<myTag:basepage>

	<table:addressestable addresses="${ sessionScope.addresses }" />
	<table:claimstable claims="${ sessionScope.claims }"/>

</myTag:basepage>
