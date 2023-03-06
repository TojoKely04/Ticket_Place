<%-- 
    Document   : achat_reservation
    Created on : 28 janv. 2023, 18:05:01
    Author     : TOJOKELY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="evenement.Evenement"%>
<%@page import="evenement.Promotion"%>
<%@page import="zone.ZonePublic"%>
<%@page import="zone.ZoneReservee"%>
<%
    Promotion[] promotion = (Promotion[])request.getAttribute("promotion");
    ZoneReservee[] zoneReservee = (ZoneReservee[])request.getAttribute("zoneReservee");
    ZonePublic zonePublic = (ZonePublic)request.getAttribute("zonePublic");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Achat ou Reservation</title>
    </head>
    <body>

        <form action="AchatPublic" method="get">
            <h2>ZONE PUBLIC</h2>
            <p>
                Place Libre : <%= zonePublic.getN_place_libre() %>  |   Prix : <%= zonePublic.getPrix() %>        
            </p>
            <input type="hidden" name="id_zone_public" value="<%= zonePublic.getId_zone_public() %>">
            <input type="hidden" name="id_evenement" value="<%= zonePublic.getId_evenement() %>">
            <input type="hidden" name="prix_unitaire" value="<%= zonePublic.getPrix() %>">          
            <input type="number" name="place_public" placeholder="nombre de place" required>     
            <input type="submit" value="Acheter">
        </form>    

        <!-- <form action="Reserver" method="get"> -->
            <h2>ZONE RESERVEE</h2>
            <table border="1" width="100%">
                <tr>
                    <th>Nom</th>
                    <th>Nombre De Place</th>
                    <th>Numero Debut</th>
                    <th>Numero Fin</th>
                    <th>Prix</th>  
                    <th>Delai</th>  
                </tr>
                  
                <% for (int i=0 ; i<zoneReservee.length ;i++ ) { %>
                    <tr>
                        <td><%= zoneReservee[i].getNom() %></td>
                        <td><%= zoneReservee[i].getN_place() %></td>
                        <td><%= zoneReservee[i].getNum_debut() %></td>
                        <td><%= zoneReservee[i].getNum_fin() %></td>
                        <td><%= zoneReservee[i].getPrix() %></td>
                        <td>
                            <% if(zoneReservee[i].getDelai()!=-1){ %>
                                <%= zoneReservee[i].getDelai() %>min
                            <% } %>
                        </td>                        
                        <td><input type="text" id="place_reservee<%= i %>" placeholder="<%= zoneReservee[i].getNum_debut() %>,<%= zoneReservee[i].getNum_debut()+1 %>..."></td>                        
                        <td>
                            <!-- <input type="submit" value="Reserver"> -->
                        <a href="" onclick="this.href='Reserver?id_evenement=<%= zonePublic.getId_evenement() %>&id_zone_reservee=<%= zoneReservee[i].getId_zone_reservee() %>&place_reservee='+document.getElementById('place_reservee<%= i %>').value">Reserver</a>
                        </td>
                        <td>
                            <a href="GetPlaceDispo?id_zone_reservee=<%= zoneReservee[i].getId_zone_reservee() %>&id_evenement=<%=zoneReservee[i].getId_evenement() %>">Voir Place Dispo</a>
                        </td>
                    </tr>
                    <!-- <input type="hidden" name="id_zone_reservee" value="<%= zoneReservee[i].getId_zone_reservee() %>"> -->
                <% } %>

            </table>
            <!-- <input type="hidden" name="id_evenement" value="<%= zonePublic.getId_evenement() %>"> -->

        <!-- </form> -->

        <% if(promotion!=null) { %>
            <h2>LISTE PROMOTION</h2>
            <% for (int i=0 ; i<promotion.length ;i++ ) { %>
                <p>
                    La zone : <%= promotion[i].getZoneReservee().getNom() %> reduit de 
                    <%= promotion[i].getReduction() %>% du 
                    <%= promotion[i].getDebut() %> jusqu'au 
                    <%= promotion[i].getFin() %>
                </p>
            <% } %>
        <% } %>

        <form action="GetReservation" method="get">
            <h2>CONFIRMER UNE RESERVATION</h2>
            <p>
                ID De Reservation : <input type="text" name="id_reservation">
                <input type="submit" value="Valider">
            </p>
        </form> 

    </body>
</html>
