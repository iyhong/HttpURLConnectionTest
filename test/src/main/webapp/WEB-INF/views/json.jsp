<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<title>Home</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h1>
	Json Test!  
</h1>

<form action="<c:url value="/json"/>" method="post">
	id:<input type="text" name="id"><br>
	name:<input type="text" name="name"><br>
	<input type="submit">
</form>
${json}

</body>
</html>
