/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bdd.Connect;
import evenement.Evenement;
import evenement.Reservation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.ArrayList;
import zone.ZonePublic;

/**
 *
 * @author TOJOKELY
 */
public class GetListeEvenement extends Controller {

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
            out.println("<title>Servlet GetListeEvenement</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetListeEvenement at " + request.getContextPath() + "</h1>");
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
        PrintWriter p = response.getWriter();
        Evenement[] evenement = null;
        ZonePublic[] zonePublic = null;
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            ArrayList<Evenement> list = (new Evenement()).select(connection, "Evenement");
            evenement=new Evenement[list.size()];
            zonePublic=new ZonePublic[list.size()];
            
            for (int i = 0; i < list.size() ; i++) {
                evenement[i]=list.get(i);
                zonePublic[i]=evenement[i].getZone_public();
            }
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
        request.setAttribute("zonePublic", zonePublic);
        
        this.dispatch(request, response, "liste_evenement.jsp");
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
