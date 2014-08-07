<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="myTag" tagdir="/WEB-INF/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<myTag:basepage>

	<form action="controller" name="delete_database">
		<input type="submit" value="Delete DB" />
	</form>
	
	<form action="controller" name="create_database">
		<input type="submit" value="Create DB"  />
	</form>

</myTag:basepage>