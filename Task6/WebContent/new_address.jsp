<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTag"%>
<%@ taglib uri="/WEB-INF/tld/tableTags.tld" prefix="table"%>

<myTag:basepage title="New address">
	<form id="new_address_form" action="controller" method="post">
		<label>Street</label> 
		<input type="text" name="street" value="" required autofocus/>
		<label>House</label>
		<input type="text" name="house" value="" required/> <label>Block</label> <input
			type="text" name="block" value="0" /> <label>Flat</label> <input
			type="text" name="flat" value="" required/>
			<input type="reset" value="Clear All"/>
			<input type="submit" value="Add Address"/>
	</form>
</myTag:basepage>