<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="current_page" value="${ pageContext.request.requestURI }" 
	scope="session" />
<c:set var="language"
	value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
	scope="session" />
	 
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string" />


<section id="headerSection" class="headerSectionStyle">
	<section id="logo"></section>
	<section id="headerButtons" class="headerButtonsStyle">

		<section id="registerButton">
			<a href="register.jsp"><fmt:message key="register" /></a>
		</section>

		<c:choose>
			<c:when test="${ not sessionScope.is_login }">
				<section id="signInButton">
					<a href="login.jsp"><fmt:message key="sign_in" /></a>
				</section>
			</c:when>

			<c:otherwise>
				<section id="signOutButton">
					<a href="/Task6/controller?command=sign_out_command"><fmt:message
							key="sign_out" /></a>
				</section>
			</c:otherwise>
		</c:choose>
	</section>
	<section id="languageSelect" class="languageSelectStyle">
		<c:choose>
			<c:when test="${ language eq 'en_US' }">
				<a
					href="/Task6/controller?command=change_language_command&language=ru_RU"><img
					src="resources/img/ru.png"></a>
			</c:when>
			<c:otherwise>
				<a
					href="/Task6/controller?command=change_language_command&language=en_US"><img
					src="resources/img/uk.png"></a>
			</c:otherwise>
		</c:choose>
	</section>
</section>