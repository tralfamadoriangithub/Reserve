<%@ tag description="Base layout tag" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="title" required="true"%>

<html>
<head>
<link href="styles/style.css" rel="stylesheet" type="text/css" />
<title><c:out value="${ title }" /></title>
</head>
<body>
	<section class="mainWrapper">
		<header id="pageHeader">
			<c:import url="header.jsp" />
		</header>
		<section class="mainContent">
			<aside id="pageAside">
				<c:import url="aside.jsp" />
			</aside>
			<section id="pageBody">
				<jsp:doBody />
			</section>
		</section>
		<footer id="pageFooter">
			<c:import url="footer.jsp"></c:import>
		</footer>
	</section>
</body>
</html>
