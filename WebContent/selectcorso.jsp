<%@page import="java.util.List"%>
<%@page import="model.Corso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>SELEZIONA CORSO</title>
</head>
<body>


<%  List<Corso> corsi = (List<Corso>) request.getAttribute("listacorsi"); %>


<h1>SELEZIONA CORSO</h1>

<form method="post" action="viewFinal">
SELEZIONA CORSO :
<select name="corsi">
<% if (corsi!= null){ %>
<%for (Corso c: corsi) {%>
<option id="<%=c.getId() %> " value="<%=c.getNome() %>"><%=c.getId()+" "+c.getNome() %></option>
<%} }%>
</select> <br>
<input  type="submit" value="CERCA">
</form>

</body>
</html>