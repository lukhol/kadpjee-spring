<%@ page session="false" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	  
	<script>
		$(document).ready(function() {
	  		$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
	  	});
	</script>

	<link href="<c:url value="/resources/css/addUser.css" />" rel="stylesheet">
	<title>Home</title>
</head>
<body>
	<div id=loginContainer>
		<form:form modelAttribute="userToAdd" action="addUser.html?${_csrf.parameterName}=${_csrf.token}" class="form-horizontal" method="post" enctype="multipart/form-data">
			<fieldset>
				<div>
					<form:hidden path="id"/>
					<form:hidden path="pesel.id" />
					<c:if test="${userToAdd.id!=0}">
						<form:hidden path="password"/>
					</c:if>
				</div>
				<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.login" /></label>
				    <div class="col-sm-10">
				      <form:input id="login" path="login" type="text" class="form-control" placeholder="Login"/>
				      <div class="errorStatement"><form:errors path = "login"/></div>
				    </div>
			  	</div>
			  	
			  	<c:if test="${userToAdd.id==0}">
				  	<div class="form-group">
					    <label class="col-sm-2 control-label"><spring:message code="label.password" /></label>
					    <div class="col-sm-10">
					      <form:input id="password" path="password" type="password" class="form-control" placeholder="Password"/>
					      <form:errors path = "password"/>
					    </div>
				  	</div>
				  </c:if>
				
				<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.email" /></label>
				    <div class="col-sm-10">
				      <form:input id="email" path="email" type="text" class="form-control" placeholder="Email"/>
				      <form:errors path = "email"/>
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.firstname" /></label>
				    <div class="col-sm-10">
				      	<form:input id="firstname" path="firstname" type="text" class="form-control" placeholder="Firstname"/>
				    	<form:errors path = "firstname"/>
				    </div>
			  	</div>
				
				<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.lastname" /></label>
				    <div class="col-sm-10">
				      <form:input id="lastname" path="lastname" type="text" class="form-control" placeholder="Lastname"/>
				      <form:errors path = "lastname"/>
				    </div>
			  	</div>
				
				<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.telephone" /></label>
				    <div class="col-sm-10">
				      <form:input id="telephone" path="telephone" type="text" class="form-control" placeholder="Telephone"/>
				      <form:errors path = "telephone"/>
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.pesel" /></label>
				    <div class="col-sm-10">
				      <form:input id="pesel.PESEL" path="pesel.PESEL" type="text" class="form-control" placeholder="Pesel"/>
				      <form:errors path = "pesel.PESEL"/>
				    </div>
			  	</div>
			  	
			  	<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.picture" /></label>
				    <div class="col-sm-10">
				      <form:input id="picture" path="picture" type="file"/>
				    </div>
			  	</div>
			  	

			  	<!-- Birthday: -->
			  	<!--  
			  	<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.birthday" /></label>
				    <div class="col-sm-10">
				      <form:input id="datepicker" path="birthday"/>
				      <form:errors path = "birthday"/>
				    </div>
			  	</div>
			  	-->
			  	
			  	<sec:authorize access = "hasRole('ROLE_ADMIN')">
			  		<div class="form-group">
				    <label class="col-sm-2 control-label"><spring:message code="label.enable" /></label>
				    <div class="col-sm-10">
        				<form:checkbox path="enabled" />
        				<form:errors path="enabled"/>
				    </div>
			  	</div>
			  	</sec:authorize>
			  	
			  	<c:if test="${userToAdd.id==0}">
			  		<input type="submit" value="Dodaj uzytkownika" class="btn btn-primary btn-lg btn-block"/>
			  	</c:if>
			  	<c:if test="${userToAdd.id!=0}">
			  		<input type="submit" value="Edytuj uzytkownika" class="btn btn-primary btn-lg btn-block"/>
			  	</c:if>
			</fieldset>		
		</form:form>
	</div>
</body>
</html>
