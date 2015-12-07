/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */ 
package edu.wctc.asp.bookwebapp.controller;

import edu.wctc.asp.bookwebapp.entity.Author;
import edu.wctc.asp.bookwebapp.service.AuthorService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author alex
 */
@WebServlet(name = "AuthorController", urlPatterns = {"/AuthorController"})
public class AuthorController extends HttpServlet {

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

        ServletContext sctx = getServletContext();
        WebApplicationContext ctx
                = WebApplicationContextUtils.getWebApplicationContext(sctx);
        AuthorService authorService = (AuthorService) ctx.getBean("authorService");

        String action = request.getParameter("action");

        if (action != null) {
            try {
                
                String authorID = request.getParameter("authorID");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                
                switch (action) {

                    case "save":

                        if(authorID == null || authorID.isEmpty()){
                            Author author = new Author(0);
                            author.setAuthorFirstName(firstName);
                            author.setAuthorLastName(lastName);
                            authorService.saveAuthor(author);
                        }
                        
                        
                        
                        break;

                    case "delete":
                        
                        String [] deleteValues = request.getParameterValues("boxes");
                        for(String id : deleteValues){
                            authorService.deleteAuthors(Integer.valueOf(id));
                        }
                        
                        
                        break;

                    default:
                        break;
                }

            } catch (Exception e) {
                request.setAttribute("error", e.toString());
            }

        }

        try {
            request.setAttribute("authorList", authorService.getAllAuthors());

        } catch (Exception e) {
            request.setAttribute("error", e.toString());
        }

        RequestDispatcher dispatcher
                = getServletContext().getRequestDispatcher("/allAuthorsView.jsp");
        dispatcher.forward(request, response);

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
        processRequest(request, response);
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
