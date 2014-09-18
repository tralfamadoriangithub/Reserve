<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="string"/>
<fmt:message var="register" key="registration"/>

<myTag:basepage title="${ register }">

	<section id="regiastrationForm" >
	
	<script type="text/javascript">
	function verifyPassword(){
		with( document.registration_form ){
			if( password.value != password_confirm.value ){
				alert( "Password and password confirm doesn't match" );
				password.value = "";
				password_confirm.value = "";
				password.focus();
				return false;
			}
			return true;
		}
	}
	</script>
	
		<form name="registration_form" action="controller" method="post" onsubmit="return verifyPassword();">
			<input type="hidden" name="command" value="register_new_user_command"/>
			<p><fmt:message key="name"/>:<br>
			<input type="text" placeholder="<fmt:message key="name"/>" name="name" required/>
			<p><fmt:message key="surname"/>:<br>
			<input type="text" placeholder="<fmt:message key="surname"/>" name="surname" required/>
			<p><fmt:message key="login"/>:<br>
			<input type="text" placeholder="<fmt:message key="login"/>" name="login" required/>
			<p><fmt:message key="password"/>:<br>
			<input type="password" name="password" placeholder="<fmt:message key="password"/>" required/>
			<p><fmt:message key="password_confirm"/>:<br>
			<input type="password" name="password_confirm" placeholder="<fmt:message key="password_confirm"/>" required/>
			<br><br>
			<input type="submit" value="${ register }"/>
		</form>
	</section>
</myTag:basepage>