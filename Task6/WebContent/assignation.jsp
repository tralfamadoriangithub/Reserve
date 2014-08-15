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
			<input type="hidden" name="command" value="register_assignation_command" />
			<section id="begin_work">
			<label><fmt:message key="begin_work"/></label>
				<input type="date" name="begin_work_date" /> 
				<input type="time" name="begin_work_time" />
			</section>
			<section id="end_work">
			<label><fmt:message key="end_work"/></label>
				<input type="date" name="end_work_date" /> 
				<input type="time" name="end_work_time" />
			</section>
			
			<table:operatorworkerstable workers="${ sessionScope.workers }" />
			
			
			<input type="submit" value='<fmt:message key="assign_squad"/>' />
		</form>
	</section>
</myTag:basepage>
