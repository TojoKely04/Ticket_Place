/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bdd.Connect;
import evenement.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.time.LocalDateTime;
import zone.ZoneReservee;

/**
 *
 * @author TOJOKELY
 */
public class Reserver extends Controller {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Reserver</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Reserver at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String place = request.getParameter("place_reservee").trim();
        String id_zone_reservee = request.getParameter("id_zone_reservee").trim();
        String id_evenement = request.getParameter("id_evenement").trim();
        
                
        LocalDateTime date_time = LocalDateTime.now();
        
        Reservation reservation = new Reservation();
        reservation.setId_evenement(id_evenement);
        reservation.setId_zone_reservee(id_zone_reservee);
        reservation.setPlace(place);
        String newPlace = reservation.changePlace();
        reservation.setPlace(newPlace);
        reservation.setDate_time_reservation(date_time.toString());
        reservation.setAnnule(0);
        reservation.setConfirmation(0);

        Connection connection = null;
        try {
            ZoneReservee zoneReservee = ZoneReservee.getZoneReserveeById(connection, id_zone_reservee);
            connection = Connect.getConnection();
            reservation.verificationPlace(connection);

            reservation.setPrix(connection, null, zoneReservee.getPrix());
            reservation.insert(connection, "reservation");
        } catch (Exception e) {
            //response.getWriter().print(e);
            this.dispatchToErrorJSP(request, response, e);
        }finally{
            try {
                connection.close();
            } catch (Exception ex) {
                this.dispatchToErrorJSP(request, response, ex);
            }
        }
        
        request.setAttribute("action", "(Votre idReservation "+reservation.getId_reservation()+") Reservation des places "+reservation.getPlace()+" ");
        this.dispatch(request, response, "index.jsp");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
