<%@page import="model.Studente"%>
<%@page import="model.Corso"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DATI CORSO STUDENTI</title>
</head>
<body>

<%
Corso c = (Corso) request.getAttribute("corso");
%>

<div>Corso <%=c.getNome() %></div> 


<h3>Studenti del corso</h3>
<% for (Studente s : c.getStudenti()){ %>
<div>Matricola : <%=s.getMatricola() %>
     Nome : <%=s.getNome() %>
     Cognome : <%=s.getCognome() %>
  

</div>

<%} %>

</body>
</html>