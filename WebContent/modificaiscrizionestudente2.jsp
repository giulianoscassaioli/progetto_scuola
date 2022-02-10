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

<%  List<Corso> listanoniscritto = (List<Corso>) request.getAttribute("listanoniscritto"); %>
<%  String matricola = (String) request.getAttribute("matricola"); %>
<%  List<Corso> listaiscritto = (List<Corso>) request.getAttribute("listaiscritto"); %>
<form method="post" action="modificaFinal">
CORSI FREQUENTATI :
<select name="corsifrequentati">
<% if (listaiscritto!= null){ %>
<%for (Corso c: listaiscritto) {%>
<option id="<%=c.getId()%> " value="<%=c.getId() %>"><%=c.getNome() %></option>
<%} }%>
</select> <br>
CORSI NON FREQUENTATI:
<select name="corsinonfrequentati">
<%for (Corso c: listanoniscritto) {%>
<option id="<%=c.getId()%> " value="<%=c.getId() %>"><%=c.getNome() %></option>
<%}%>
</select> <br>
<input type="hidden" name="matricola" value="<%=matricola  %>">
<input  type="submit" value="MODIFICA">
</form>
</body>
</html>