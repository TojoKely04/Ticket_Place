<%-- 
    Document   : confirmation
    Created on : 28 janv. 2023, 18:42:06
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evenement.Evenement"%>
<%@page import="zone.ZoneReservee"%>
<%@page import="evenement.Reservation"%>
<%
    Reservation reservation = (Reservation)request.getAttribute("reservation");
    ZoneReservee zoneReservee = reservation.getZone();

    int nombreDePlace = reservation.getPlace().split(",").length;
    double prix = nombreDePlace*zoneReservee.getPrix();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="ValiderReservation" method="get">
            <h2>Confirmer Votre Reservation</h2>
            <p>ID Reservation : <%= reservation.getId_reservation() %></p>
            <p>Zone : <%= zoneReservee.getNom() %></p>
            <p>Nombre De Place : <%= nombreDePlace %></p>
            <p>Numero De Place : <%= reservation.getPlace() %></p>
            <p>Prix : <%= prix %></p>
            <input type="hidden" name="id_reservation" value="<%= reservation.getId_reservation() %>">
            <input type="submit" value="Payer">
        </form>
            <br>
            <a href="AnnulerReservation?id_reservation=<%= reservation.getId_reservation() %>">Annuler</a>
    </body>
</html>
