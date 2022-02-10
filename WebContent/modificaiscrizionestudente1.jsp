<%@page import="model.Studente"%>
<%@page import="model.Corso"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%  List<Studente> studenti = (List<Studente>) request.getAttribute("listastudenti"); %>

<h1>MODIFICA ISCRIZIONE</h1>

<form method="post" action="modifica2">
MATRICOLA STUDENTE :
<select name="studenti">
<% if (studenti!= null){ %>
<%for (Studente s: studenti) {%>
<option id="<%=s.getMatricola()%> " value="<%=s.getMatricola() %>"><%=s.getMatricola()+" "+s.getNome() %></option>
<%} }%>
</select> <br>
<input  type="submit" value="SELEZIONA">
</form>

</body>
</html>