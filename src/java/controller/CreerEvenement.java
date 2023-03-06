/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bdd.Connect;
import evenement.Evenement;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import zone.ZonePublic;

/**
 *
 * @author TOJOKELY
 */
public class CreerEvenement extends Controller {

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
            out.println("<title>Servlet CreerEvenement</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreerEvenement at " + request.getContextPath() + "</h1>");
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
        String nom = request.getParameter("nom");
        Date date = Date.valueOf(request.getParameter("date").trim());
        int nZone = Integer.parseInt(request.getParameter("privee").trim());
        int nPlacePublic = Integer.parseInt(request.getParameter("public").trim());
        double prixPublic = Double.parseDouble(request.getParameter("prix").trim());
        
        Evenement evenement = new Evenement();
        evenement.setNom(nom);
        evenement.setDate_evenement(date);
        evenement.setN_zone(nZone);
        
        ZonePublic zonePublic = new ZonePublic();
        zonePublic.setN_place_total(nPlacePublic);
        zonePublic.setPrix(prixPublic);
        
        Connection connection = null;
        
        try {
            connection = Connect.getConnection();
            evenement.insert(connection, "evenement");
            zonePublic.setId_evenement(evenement.getId_evenement());
            zonePublic.insert(connection, "zonePublic");
        } catch (Exception e) {
            this.dispatchToErrorJSP(request, response, e);
        }finally{
            try {
                connection.close();
            } catch (Exception ex) {
                this.dispatchToErrorJSP(request, response, ex);
            }
        }
        
        request.setAttribute("evenement", evenement);
        this.dispatch(request, response, "configuration_zone.jsp");
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
