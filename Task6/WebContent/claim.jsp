<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Claim</title>
</head>
<myTag:basepage>
	<section id="claimForm">
		<h3>Claim for address:</h3>
		<br>
		<h5>Street:</h5><c:out value="${ sessionScope.address.street }"/><br>
		<h5>House:</h5><c:out value="${ sessionScope.address.houseNumber }"/><br>
		<h5>Block:</h5><c:out value="${ sessionScope.address.blockNumber}"/><br>
		<h5>Flat:</h5><c:out value="${ sessionScope.address.flatNumber }"/><br> 
		<h5>Discribe your problem:</h5>
		<br>
		<form action="controller" method="post">
			<input type="hidden" name="command" value="send_claim_command">
			<input type="text" name="problem" value="">
			<input type="submit" value="Send claim">
		</form>
	</section>

</myTag:basepage>