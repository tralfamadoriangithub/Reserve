<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="table"%>

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />
<fmt:message var="title" key="assignation" />

<myTag:basepage title="${ title }">
	<section id="assignation_section">

		<br>
		<form action="controller" method="post">
			<input type="hidden" name="command"
				value="register_assignation_command" />
			<section id="assignation_date_time" style="margin-left:70px">
				<section id="begin_work" style="float: left">
					<label><strong><fmt:message key="begin_work" /></strong></label> <br> <input
						type="date" name="begin_work_date" required /> <input type="time"
						name="begin_work_time" required />
				</section>
				<section id="end_work">
					<label><strong><fmt:message key="end_work" /></strong></label> <br> <input
						type="date" name="end_work_date" required /> <input type="time"
						name="end_work_time" required />
				</section>
			</section>
			<br>
			<h3 class="tableLabel">
				<fmt:message key="workers" />
			</h3>
			<table:operatorworkerstable workers="${ sessionScope.workers }" />
			<br> <input type="submit"
				value='<fmt:message key="assign_squad"/>' />
		</form>
	</section>
</myTag:basepage>
