/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bdd.Connect;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import zone.ZoneReservee;

/**
 *
 * @author TOJOKELY
 */
public class CreerZonePrivee extends Controller {

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
            out.println("<title>Servlet CreerZonePrivee</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreerZonePrivee at " + request.getContextPath() + "</h1>");
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
        int taille = Integer.parseInt(request.getParameter("nZone").trim());
        ZoneReservee[] zoneReservees = new ZoneReservee[taille];
        for(int i=0 ; i<taille ; i++){
            zoneReservees[i]=new ZoneReservee();
            zoneReservees[i].setId_evenement(request.getParameter("id_evenement").trim());
            zoneReservees[i].setNom(request.getParameter("nom"+i).trim());
            zoneReservees[i].setN_place(Integer.parseInt(request.getParameter("nplace"+i).trim()));;
            zoneReservees[i].setNum_debut(Integer.parseInt(request.getParameter("debut"+i).trim()));
            zoneReservees[i].setNum_fin(Integer.parseInt(request.getParameter("fin"+i).trim()));
            zoneReservees[i].setPrix(Integer.parseInt(request.getParameter("prix"+i)));
            
            if(request.getParameter("delai"+i).trim()==""){
                zoneReservees[i].setDelai(-1);                
            }else{
                zoneReservees[i].setDelai(Integer.parseInt(request.getParameter("delai"+i).trim()));                
            }
        }
        
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            for(int i=0 ; i<taille ; i++){
                zoneReservees[i].insert(connection, "zonereservee");
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
        
        request.setAttribute("zoneReservee", zoneReservees);
        this.dispatch(request, response, "creation_promotion.jsp");
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
