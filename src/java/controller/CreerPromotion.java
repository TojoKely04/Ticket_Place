/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bdd.Connect;
import evenement.Promotion;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;

/**
 *
 * @author TOJOKELY
 */
public class CreerPromotion extends Controller {

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
            out.println("<title>Servlet CreerPromotion</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreerPromotion at " + request.getContextPath() + "</h1>");
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
        String id_evenement = request.getParameter("id_evenement").trim();
        Promotion[] promotion = new Promotion[taille];
        for(int i=0 ; i<taille ; i++){
            if(!request.getParameter("debut"+i).trim().isEmpty() || !request.getParameter("fin"+i).trim().isEmpty() || !request.getParameter("reduction"+i).isEmpty()){
                promotion[i]=new Promotion();
                promotion[i].setDebut(request.getParameter("debut"+i).trim());
                promotion[i].setFin(request.getParameter("fin"+i).trim());
                promotion[i].setId_evenement(id_evenement);
                promotion[i].setId_zone_reservee(request.getParameter("id_zone"+i));
                promotion[i].setReduction(Double.parseDouble(request.getParameter("reduction"+i)));
            }
        }
        
        Connection connection = null;
        try {
            connection = Connect.getConnection();
            for(int i=0 ; i<taille ; i++){
                if(promotion[i]!=null){
                    promotion[i].insert(connection, "promotion");
                }
            }
        } catch (Exception e) {
            response.getWriter().print(e);
            //this.dispatchToErrorJSP(request, response, e);
        }finally{
            try {
                connection.close();
            } catch (Exception ex) {
                this.dispatchToErrorJSP(request, response, ex);
            }
        }
        
        request.setAttribute("action", "Creation D'Evenement");
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
