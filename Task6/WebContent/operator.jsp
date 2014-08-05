<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="tabletag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OPERATOR</title>
</head>
<myTag:basepage>
	<h1>Operator</h1>
	<tabletag:assignationstable
		assignations="${ sessionScope.assignations }" />
	<tabletag:workerstable workers="${ sessionScope.workers }" />
</myTag:basepage>
