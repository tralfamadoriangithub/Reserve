<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />
<fmt:message var="title" key="update_claim" />

<myTag:basepage title="${ title }">
	<section id="claimForm">
		<h3>
			<fmt:message key="update_claim" />:</h3>
		<br>
		<table>
			<tr>
				<td><strong><fmt:message key="address" /></strong></td>
			</tr>
			<tr>
				<td><strong><fmt:message key="street" />: </strong> <c:out
						value="${ sessionScope.editclaim_for_editedClaim.address.street }" />, <strong>
						<fmt:message key="house" />:
				</strong> <c:out value="${ sessionScope.claim_for_edit.address.houseNumber }" />, <c:if
						test=" ${ sessionScope.claimAddress.blockNumber != 0 }">
						<strong><fmt:message key="block" />: </strong>
						<c:out value="${ sessionScope.claim_for_edit.address.blockNumber}" />,
					</c:if> <strong> <fmt:message key="flat" />:
				</strong> <c:out value="${ sessionScope.claim_for_edit.address.flatNumber }" /></td>
			</tr>
			<tr>
				<td><br><h5>
						<fmt:message key="describe_problem" />
						:
					</h5></td>
			</tr>
			<tr>
				<td><form action="controller" method="post">
						<input type="hidden" name="command" value="update_claim_command" />
						<input type="hidden" name="claim_id" value="${ sessionScope.claim_for_edit.claimId }"/>
						<textarea rows="4" cols="50" name="problem_description" style="border-radius: 4px" required><c:out value="${ sessionScope.claim_for_edit.problemDescription }"></c:out></textarea>
						<br> <input type="submit"
							value='<fmt:message key="update_claim" />' />
					</form></td>
			</tr>
		</table>
		
	</section>

</myTag:basepage>