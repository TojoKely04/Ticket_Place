<%-- 
    Document   : error
    Created on : 28 janv. 2023, 22:43:10
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% String message = (String)request.getAttribute("error");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2><%= message %></h2>
    </body>
</html>
