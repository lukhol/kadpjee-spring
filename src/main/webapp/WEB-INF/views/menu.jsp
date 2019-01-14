<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
<nav class="mainNav">
	<!-- Form oraz skrypt JS od wylogowania sie -->
	
	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<ol id="mymenu">
		<sec:authorize access = "isAnonymous()">
			<li><a href="/edukacja/login.html"><spring:message code="label.loginToApp"/></a></li>
		</sec:authorize>
		<sec:authorize access = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
			<li><a href="javascript:formSubmit()"><spring:message code="label.logout"/></a></li>
		</sec:authorize>
		<li><a href="/edukacja/addUser.html"><spring:message code="label.addUser"/></a></li>
		<sec:authorize access = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
			<li><a href="/edukacja/listOfUsers.html"><spring:message code="label.userList"/></a></li>
		</sec:authorize>
		<sec:authorize access = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
			<li><a href="/edukacja/userDetails.html"><spring:message code="label.userDetails"/></a></li>
		</sec:authorize>
		<div style="clear:both"></div>
	</ol>
</nav>