<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="table"%>

<myTag:basepage title="New address">
	<form id="new_address_form" action="controller" method="post">
		<table>
			<tr>
				<td>Street</td>
				<td><input type="text" name="street" value="" required
					autofocus /></td>
			</tr>
			<tr>
				<td>House</td>
				<td><input type="text" name="house" value="" required /></td>
			</tr>
			<tr>
				<td>Block</td>
				<td><input type="text" name="house" value="" required /></td>
			</tr>
			<tr>
			<td>Flat</td><td><input type="text" name="flat" value="" required /></td>
			</tr>
		</table>
		<section id="newaddressLabel">
			<label>Street</label> <label>House</label> <label>Block</label> <label>Flat</label> <label>Phone</label>
		</section>
		<section id="newAddressField">
			<input type="hidden" name="userId"
				value="${ sessionScope.user.userId }" /> <input type="text"
				name="street" value="" required autofocus /> <input type="text"
				name="house" value="" required /> <input type="text" name="block"
				value="" /> <input type="text" name="flat" value="" required />
				<input type="text" name="phone" value="" required />
		</section>
		<input type="reset" value="Clear All" /> <input type="submit"
			value="Add Address" />
	</form>
</myTag:basepage>