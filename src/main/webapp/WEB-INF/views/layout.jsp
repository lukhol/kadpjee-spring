<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<style>
	body{
	margin: 0;
	background-image: url('/edukacja/resources/images/b12.png');
	text-align: center;
	color:white;
	}
	#container{
		margin-left:auto;
		margin-right: auto;
		width: 100%;
		padding: 0;
		min-height: 600px;
	}
</style>
</head>
<body> 
	<div id="container">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="menu" />
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>