/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bdd.Connect;
import evenement.AchatZonePublic;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import zone.ZonePublic;

/**
 *
 * @author TOJOKELY
 */
public class AchatPublic extends Controller {

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
            out.println("<title>Servlet AchatPublic</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AchatPublic at " + request.getContextPath() + "</h1>");
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
        PrintWriter p =response.getWriter();
        
        double prix_unitaire = Double.parseDouble(request.getParameter("prix_unitaire").trim());
        String id_evenement = request.getParameter("id_evenement").trim();
        String id_zone_public = request.getParameter("id_zone_public").trim();
        int n_place = Integer.parseInt(request.getParameter("place_public").trim());
                
        AchatZonePublic achat = new AchatZonePublic();
        achat.setId_evenement(id_evenement);
        achat.setId_zone_public(id_zone_public);
        achat.setN_place(n_place);
        achat.setPrix(prix_unitaire*n_place);
        Connection connection = null;
        
        
        try {
            connection = Connect.getConnection();
            ZonePublic zonePublic = ZonePublic.getZonePublicByID(connection , id_zone_public);
            if(n_place<=0){
                this.dispatchToErrorJSP(request, response, new Exception("Nombre <= 0"));
                return;
            }
            if(zonePublic.getN_place_libre()==0){
                this.dispatchToErrorJSP(request, response, new Exception("Il n'y a plus de place disponible"));
                return;
            }
            if(zonePublic.getN_place_libre()<n_place){
                this.dispatchToErrorJSP(request, response, new Exception("Nombre de place disponible insuffisant"));
                return;
            }

            achat.insert(connection, "AchatZonePublic");
        } catch (Exception e) {
            this.dispatchToErrorJSP(request, response, e);
        }finally{
            try {
                connection.close();
            } catch (Exception ex) {
                this.dispatchToErrorJSP(request, response, ex);
            }
        }
        
        request.setAttribute("action", "Achat de "+n_place+" billet public ");
        this.dispatch(request, response,"index.jsp");
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
