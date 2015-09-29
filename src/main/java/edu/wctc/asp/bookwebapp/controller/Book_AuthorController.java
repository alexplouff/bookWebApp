/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.controller;

import edu.wctc.asp.bookwebapp.bookservice.BookService;
import edu.wctc.asp.bookwebapp.lowlevel.BookDAO;
import edu.wctc.asp.bookwebapp.lowlevel.SQL_Accessor;
import edu.wctc.asp.bookwebapp.lowlevel.SQL_Data_Provider;
import edu.wctc.asp.bookwebapp.model.BookStrategy;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alex
 */
@WebServlet(name = "Book_AuthorController", urlPatterns = {"/bookAuthorControls"})
public class Book_AuthorController extends HttpServlet {

    
    private String resultPage;
    
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

        ServletContext ctx = request.getServletContext();
        String driverClass = (String) ctx.getInitParameter("db.driverName");
        String dbURL = (String) ctx.getInitParameter("db.driver.url");
        String dbUserName = (String) ctx.getInitParameter("db.userName");
        String password = (String) ctx.getInitParameter("db.password");
        
        BookService service = new BookService(new BookDAO(new SQL_Accessor(
                new SQL_Data_Provider(driverClass, dbURL, dbUserName, password))));

        List<BookStrategy> bookRecords;

        try {
            bookRecords = service.getAllBookRecords();
            request.setAttribute("bookRecordsResult", bookRecords);
            resultPage = "book_authorRecords.jsp";
        } catch (SQLException | ClassNotFoundException | ParseException ex) {
            request.setAttribute("bookRecordsResult", ex.toString());
        }

        
                RequestDispatcher view = request.getRequestDispatcher(resultPage);
        view.forward(request, response);
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
