<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
body{
background:#a2a8d3

}

</style>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%   String messaggio= (String) request.getAttribute("messaggio"); %>

<h1><%=messaggio %> </h1>

</body>
</html>