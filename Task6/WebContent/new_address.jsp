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

<myTag:basepage title="New address">
	<section id="newAddressSection">
		<h3>
			<fmt:message key="new_address" />
		</h3>
		<br>
		<form id="newAddressForm" action="controller" method="post">
			<input type="hidden" name="command" value="register_new_address_command"/>
			<dl>
				<dt>
					<fmt:message key="street" />
				</dt>
				<dd>
					<input type="text" name="street" value="" required autofocus />
				</dd>

				<dt>
					<fmt:message key="house" />
				</dt>
				<dd>
					<input type="number" name="house" required />
				</dd>

				<dt>
					<fmt:message key="block" />
				</dt>
				<dd>
					<input type="number" name="block" />
				</dd>

				<dt>
					<fmt:message key="flat" />
				</dt>
				<dd>
					<input type="number" name="flat" required />
				</dd>

				<dt>
					<fmt:message key="phone" />
				</dt>
				<dd>
					<input type="number" name="phone" required />
				</dd>

				<dt></dt>
				<dd>
					<input class="whiteButton" type="reset"
						value="<fmt:message key="clear"/>" /> <input class="whiteButton"
						type="submit" value='<fmt:message key="add_address"/>' />
				</dd>

			</dl>
		</form>
	</section>
</myTag:basepage>