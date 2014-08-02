<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="currentPage" value="${ pageContext.request.requestURI }"
	scope="session" />

<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />


<section id="headerSection" class="headerSectionStyle">
	<section id="logo"></section>
	<section id="loginButtons" class="loginButtonsStyle">
		<form action="controller" method="post">
			<fmt:message var="register" key="button.register" />
			<input type="hidden" name="command" value="register_command" /> <input
				type="submit" value="${ register }" />
		</form>
		<c:if test="${not session.login }">
			<form action="controller" method="post">
				<fmt:message var="signIn" key="button.sign_in" />
				<input type="hidden" name="command" value="sign_in_command" /> <input
					type="submit" value="${ signIn }" />
			</form>
		</c:if>
		<c:if test="${ session.login }">
			<form action="controller" method="post">
				<fmt:message var="signOut" key="button.sign_out" />
				<input type="hidden" name="command" value="sign_in_command" /> <input
					type="submit" value="${ signOut }" />
			</form>
		</c:if>
	</section>
	<section id="languageSelect" class="languageSelectStyle">
		<c:choose>
			<c:when test="${ language eq 'en_US' }">
				<a href="/Task6/controller?lang=ru&command=change_language_command"><img
					src="resources/img/ru.png"></a>
			</c:when>
			<c:otherwise>
				<a href="/Task6/controller?lang=en&command=change_language_command"><img
					src="resources/img/uk.png"></a>
			</c:otherwise>
		</c:choose>
	</section>
</section>