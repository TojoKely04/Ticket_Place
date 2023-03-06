<%-- 
    Document   : promotion
    Created on : 28 janv. 2023, 19:10:38
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evenement.Evenement"%>
<%@page import="evenement.Promotion"%>
<%@page import="zone.ZoneReservee"%>
<%
    ZoneReservee[] zoneReservee = (ZoneReservee[])request.getAttribute("zoneReservee");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <form action="CreerPromotion" method="">
            <h2>Promotion</h2>
            <input type="hidden" name="nZone" value="<%= zoneReservee.length %>">
            <input type="hidden" name="id_evenement" value="<%= zoneReservee[0].getId_evenement() %>">  
            
            <table border="1">
                <tr>
                    <th>Zone</th>
                    <th>Reduction</th>
                    <th>Date Debut</th>
                    <th>Date Fin</th>
                </tr>
                  
                <% for (int i=0 ; i<zoneReservee.length ;i++ ) { %>
                    <input type="hidden" name="id_zone<%= i %>" value="<%= zoneReservee[i].getId_zone_reservee() %>">  

                    <tr>
                        <td><%= zoneReservee[i].getNom() %></td>
                        <td><input type="number" name="reduction<%= i %>"></td>
                        <td><input type="date" name="debut<%= i %>"></td>
                        <td><input type="date" name="fin<%= i %>" placeholder="en minute"></td>
                    </tr>
                <% } %>

            </table>
            <br>
            <input type="submit" value="Valider">
        </form>
        <br>
        <form action="index.jsp" method="get">        
            <input type="submit" value="Quitter">
        </form>
    </body>
</html>
