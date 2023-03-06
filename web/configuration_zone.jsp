<%-- 
    Document   : configuration_zone
    Created on : 28 janv. 2023, 19:01:28
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evenement.Evenement"%>
<%
    Evenement evenement = (Evenement)request.getAttribute("evenement");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <center>
        <form action="CreerZonePrivee" method="get">
            <h2>Configuration Des Zones Privees</h2>
            <input type="hidden" name="nZone" value="<%= evenement.getN_zone() %>">
            <input type="hidden" name="id_evenement" value="<%= evenement.getId_evenement() %>">            

            <table border="1">
                <tr>
                    <th></th>
                    <th>Nom</th>
                    <th>Nombre De Place</th>
                    <th>Numero Debut</th>
                    <th>Numero Fin</th>
                    <th>Prix</th>  
                    <th>Delai</th>                      
                </tr>
                  
                <% for (int i=0 ; i<evenement.getN_zone() ;i++ ) { %>
                    <tr>
                        <td>Zone <%= i+1 %></td>
                        <td><input type="text" name="nom<%= i %>" required></td>
                        <td><input type="number" name="nplace<%= i %>" required></td>
                        <td><input type="number" name="debut<%= i %>" required></td>
                        <td><input type="number" name="fin<%= i %>" required></td>
                        <td><input type="number" name="prix<%= i %>" required></td>
                        <td><input type="number" name="delai<%= i %>"></td>                        
                    </tr>
                <% } %>

            </table>
            <br>
            <input type="submit" value="Valider">
        </form>
        </center>
    </body>
</html>
