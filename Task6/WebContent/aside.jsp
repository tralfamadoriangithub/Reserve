<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string"/>

<ul class="asideList">
	<li><a href="/Task6"><fmt:message key="home_page"/></a></li>
	<li><a href="${ sessionScope.user_page }"><fmt:message key="user_page"/></a></li>
	<li><a>Item</a></li>
	<li><a>Item</a></li>
	<li><a>Item</a></li>
	<li><a>Item</a></li>
	<li><a>Item</a></li>
</ul>