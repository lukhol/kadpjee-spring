<%@ page session="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<link href="<c:url value="/resources/css/listOfUsers.css" />" rel="stylesheet">
	<title>Lista użytkowników</title>
</head>
<body>
	<c:choose>
			<c:when test="${empty listOfUsers}">
				<spring:message code="label.listOfUsers.emptyList"/>
			</c:when>
			<c:otherwise>
				<table id="tableOfUsers">
					<tr>
						<th><spring:message code="label.login" /></th>
						<th><spring:message code="label.email" /></th>
						<th><spring:message code="label.firstname" /></th>
						<th><spring:message code="label.lastname" /></th>
						<sec:authorize access = "hasRole('ROLE_ADMIN')">
							<th><spring:message code="label.telephone" /></th>
							<th><spring:message code="label.password" /></th>
							<th><spring:message code="label.pesel" /></th>
							<th><spring:message code="label.deleteUser" /></th>
							<th><spring:message code="label.editUser" /></th>
						</sec:authorize>
					</tr>
					
					<c:forEach items="${listOfUsers}" var="user">
						<tr>
							<td>${user.login}</td>
							<td>${user.email}</td>
							<td>${user.firstname}</td>
							<td>${user.lastname}</td>
							<sec:authorize access = "hasRole('ROLE_ADMIN')">
								<td>${user.telephone}</td>
								<td>${user.password}</td>
								<td>${user.pesel.PESEL}</td>
								<td><a href="deleteUser/${user.id}.html" >delete</a></td>
								<td><a href="addUser?userId=${user.id}">edit</a></td>
							</sec:authorize>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
	</c:choose>
</body>
</html>
