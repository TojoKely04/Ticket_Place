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
import java.util.Vector;
import zone.ZoneReservee;

/**
 *
 * @author ITU
 */
public class GetPlaceDispo extends Controller {

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
            out.println("<title>Servlet GetPlaceDispo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetPlaceDispo at " + request.getContextPath() + "</h1>");
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
        String id_zone_reservee = request.getParameter("id_zone_reservee").trim();
        String id_evenement = request.getParameter("id_evenement").trim();
        
        Connection connection = null;
        ZoneReservee zoneReservee = null;
        Reservation[] reservation = null;
        Vector<Integer> place_attente = new Vector<Integer>();
        Vector<Integer> place_reservee = new Vector<Integer>();
        try{
            connection = Connect.getConnection();
            zoneReservee = ZoneReservee.getZoneReserveeById(connection, id_zone_reservee);
            reservation = Reservation.getReservationEvenement(connection, id_evenement, id_zone_reservee);

            for(int i=0 ; i<reservation.length ;i++){
                if(reservation[i].getConfirmation()==1){
                    for(int j=0 ; j<reservation[i].getListe_place().length ;j++){
                        place_reservee.add(reservation[i].getListe_place()[j]);
                    }
                }
                if(reservation[i].getConfirmation()==0 && reservation[i].getAnnule()==0){
                    for(int j=0 ; j<reservation[i].getListe_place().length ;j++){
                        place_attente.add(reservation[i].getListe_place()[j]);
                    }
                }
            }
 
        }catch(Exception e){
            this.dispatchToErrorJSP(request, response, e);
        }finally{
            try {
                connection.close();
            } catch (Exception e) {
            }
        }
        
        request.setAttribute("zoneReservee", zoneReservee);
        request.setAttribute("reservation", reservation);
        request.setAttribute("attente", place_attente);
        request.setAttribute("reservee", place_reservee);
        
        this.dispatch(request, response, "place_disponible.jsp");
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
