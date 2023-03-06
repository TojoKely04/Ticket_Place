/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author TOJOKELY
 */
public class Controller extends HttpServlet {

    public void dispatchToErrorJSP(HttpServletRequest request , HttpServletResponse response, Exception exception) throws ServletException, IOException {
        request.setAttribute("error",exception.getMessage());
        this.dispatch(request,response,"/error.jsp");
    }

    public void dispatch(HttpServletRequest request , HttpServletResponse response , String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request,response);
    }

}
