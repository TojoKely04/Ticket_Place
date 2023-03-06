<%-- 
    Document   : place_disponible
    Created on : 30 janv. 2023, 13:22:04
    Author     : ITU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="zone.ZoneReservee" %>
<%@page import="evenement.Reservation" %>
<%@page import="java.util.Vector" %>
<%@page import="java.lang.Integer" %>
<%
    ZoneReservee zoneReservee = (ZoneReservee)request.getAttribute("zoneReservee");
    Reservation[] reservation = (Reservation[])request.getAttribute("reservation");
    
    Vector<Integer> place_attente = (Vector<Integer>)request.getAttribute("attente");
    Vector<Integer> place_reservee = (Vector<Integer>)request.getAttribute("reservee");
    
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Place Libre</title>
        <style>
            .place{
                float: left;
                width: 100px;
                height: 20px;
                border: solid;
                margin-left: 10px;
                margin-top: 10px;
                font-size: 15px;
            }
            .zone{
                width: 1200px;  
            }
            .reservee{
                color: red;
            }
            .attente{
                color: green;
            }
            .libre{
                color: blue;
            }
        </style>
    </head>
    <body>
    <center>
        <h2>Liste Des Places Dans La Zone : <%= zoneReservee.getNom() %></h2>
        <div class="info">
            <div class="libre">
                PLACE LIBRE
            </div>
            <div class="attente">
                PLACE EN ATTENTE
            </div>
            <div class="reservee">
                PLACE RESERVEE
            </div>
        </div>
        <div class="zone">
            <% for(int i=0 ; i<zoneReservee.getN_place() ; i++){ %>
                <% boolean b = false;%>
                <% for(int j=0 ; j<place_attente.size() ; j++){%>
                    <% if(zoneReservee.getNum_debut()+i==place_attente.get(j)){ %>
                        <div class="place attente">
                            <%= zoneReservee.getNum_debut()+i %>
                            <% b=true; %>
                        </div>
                    <% } %>
                <% } %>
                <% for(int j=0 ; j<place_reservee.size() ; j++){%>
                    <% if(zoneReservee.getNum_debut()+i==place_reservee.get(j)){ %>
                        <div class="place reservee">
                            <%= zoneReservee.getNum_debut()+i %>
                            <% b=true; %>
                        </div>
                    <% } %>
                <% } %>
                <% if(b==false){ %>
                    <div class="place libre">
                        <%= zoneReservee.getNum_debut()+i %>
                    </div>
                <% } %>
            <% } %>
        </div>

    </center>
    </body>
</html>
