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
	<section id="headerButtons" class="headerButtonsStyle">
	
		<section id="registerButton">
			<a href="register.jsp"><fmt:message key="button.register"/></a>
		</section>
		
		<c:choose>
			<c:when test="${ not sessionScope.login }">
				<section id="signInButton">
					<a href="login.jsp"><fmt:message key="button.sign_in"/></a>
				</section>
			</c:when>
			
			<c:otherwise>
				<section id="signOutButton">
					<a href="logout.jsp"><fmt:message key="button.sign_out"/></a>
				</section>
			</c:otherwise>
		</c:choose>

	<%-- 	
		<c:if test="${ session.login }">

			<section id="signOutButton">
				<a href="Register.jsp">Sign Out</a>
			</section>

			<form action="controller" method="post">
				<fmt:message var="signOut" key="button.sign_out" />
				<input type="hidden" name="command" value="sign_in_command" /> <input
					type="submit" value="${ signOut }" />
			</form>

		</c:if> --%>
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