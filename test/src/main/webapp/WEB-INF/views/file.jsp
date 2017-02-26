<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	HttpUrlConnection Test!  
</h1>

<form action="<c:url value="/file"/>" method="post" enctype="multipart/form-data">
	file:<input type="file" name="file"><br>
	<input type="submit">
</form>


</body>
</html>
