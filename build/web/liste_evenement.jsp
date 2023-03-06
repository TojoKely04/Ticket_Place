<%-- 
    Document   : liste_evenement.jsp
    Created on : 28 janv. 2023, 17:56:43
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evenement.Evenement"%>
<%@page import="zone.ZonePublic"%>
<%
    Evenement[] evenement = (Evenement[])request.getAttribute("evenement");
    ZonePublic[] zonePublic = (ZonePublic[])request.getAttribute("zonePublic");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste Des Evenements</title>
    </head>
    <body>
        <center>
        <h1>Liste Des Evenements</h1>
            <table border="1" width="70%">
                <tr>
                    <th>Nom</th>
                    <th>Nombre De Zone Privee</th>
                    <th>Nombre De Place Public</th>
                    <th>Date</th>
                    <th></th>
                </tr>
                <% for (int i=0 ; i<evenement.length ;i++ ) { %>
                    <tr>
                        <td><%= evenement[i].getNom() %></td>
                        <td><%= evenement[i].getN_zone() %></td>
                        <td><%= zonePublic[i].getN_place_total() %></td>
                        <td><%= evenement[i].getDate_evenement() %></td>
                        <td><a href="AcheterReserver?id_evenement=<%= evenement[i].getId_evenement() %>"> Acheter/Reserver </a></td>
                    </tr>
                <% } %>
            </table>
        </center>
    </body>
</html>
