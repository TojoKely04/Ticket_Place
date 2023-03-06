<%-- 
    Document   : index
    Created on : 28 janv. 2023, 17:54:53
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evenement.Evenement"%>
<%@page import="zone.ZoneReservee"%>
<%@page import="evenement.Reservation"%>
<% String action = (String)request.getAttribute("action"); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="creation_evenement.jsp" method="get">
            <h2>CREER UNE EVENEMENT</h2>
            <p>
                <input type="submit" value="Creer">
            </p>
        </form>  

        <form action="GetListeEvenement" method="get">
            <h2>ACHETER OU RESERVER</h2>
            <p>
                <input type="submit" value="Voir Liste Evenement">
            </p>
        </form> 

        <form action="GetReservation" method="get">
            <h2>CONFIRMER UNE RESERVATION</h2>
            <p>
                ID De Reservation : <input type="text" name="id_reservation">
                <input type="submit" value="Valider">
            </p>
        </form>  
        
        <p style="color:green">
            <% if(action!=null) { %> 
                <%=action %> Reussi
            <% }%>
        </p>
    </body>
</html>
