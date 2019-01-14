<%@ page session="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<link href="<c:url value="/resources/css/userDetails.css" />" rel="stylesheet">
	<title></title>
</head>
<body>
	Aktualnie zalogowany uzytkownik: ${nameofuser } <br>
	Id:${user.id}  <br>
	${user.login}  <br>
	${user.email}  <br>
	${user.firstname}  <br>
	${user.lastname}  <br>
	${user.telephone}  <br>
	${user.pesel.PESEL}  <br>
</body>
</html>
