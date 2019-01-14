<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<link href="<c:url value="/resources/css/header.css" />" rel="stylesheet">

<header id="mainHeader">
	
	<span style="float: left">
    	<a href="/edukacja">Home(potem fontello)</a>
	</span>

	<span style="float: right">
    	<a href="?lang=pl">pl</a> | <a href="?lang=en">en</a> | <a href="?lang=de">de</a>
	</span>
	<div style="clear:both"></div>
	
	<h3><spring:message code="label.header"/></h3>
</header>