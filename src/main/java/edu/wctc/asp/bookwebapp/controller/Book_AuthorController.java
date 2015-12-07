/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.controller;

import edu.wctc.asp.bookwebapp.bookservice.BookService;
import edu.wctc.asp.bookwebapp.lowlevel.DAO_Strategy;
import edu.wctc.asp.bookwebapp.lowlevel.DatabaseAccessorStrategy;
import edu.wctc.asp.bookwebapp.lowlevel.SQL_Accessor;
import edu.wctc.asp.bookwebapp.lowlevel.SQL_Data_Provider;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alex
 */
//@WebServlet(name = "Book_AuthorController", urlPatterns = {"/bookAuthorControls"})
public class Book_AuthorController extends HttpServlet {

    private String resultPage;
    private ServletContext ctx;
    private String driverClass;
    private String dbURL;
    private String dbUserName;
    private String password;
    private String dbStrategyClassName;
    private String daoClassName;
    private String sqlDataClass;
    
    private final static String ERROR_PAGE = "errorPage.jsp";
    private final static String LOGIN_ERROR_MESSAGE = "Was Not Able To Log You In";
            

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
//        BookService service = new BookService(new BookDAO(new SQL_Accessor(
//                new SQL_Data_Provider(driverClass, dbURL, dbUserName, password))));
        try {
            DAO_Strategy dao = injectWithDependancies();
            
            BookService service = new BookService(dao);
            String loginAction = request.getParameter("loginAction");
            if (loginAction != null) {
                HttpSession session;
                switch (loginAction) {
                    case "login":
                        session = request.getSession();
                        String user = request.getParameter("userName");
                        session.setAttribute("user", user);
                        break;
                    case "logout":
                        session = request.getSession(false);
                        if (session != null) {
                            session.invalidate();
                            response.sendRedirect("index.html");
                            return;
                        }
                        break;
                    default:
                }
            } 

            String action = request.getParameter("action");

            if (action != null) {
                List<String> values;
                try {
                    values = new ArrayList<>();
                    switch (action) {
                        case "save": //                                 Ecompasses Save and Update
                            values.add(request.getParameter("title"));
                            values.add(request.getParameter("datePublished"));
                            values.add(request.getParameter("authorID"));
                            String bookID = request.getParameter("bookID");
                            String authorID = request.getParameter("authorID");
                            
                            if (bookID.matches("\\d+")) {            // Update
                                values.add(0, bookID);
                                
                                service.updateBookByID(values);
                                request.setAttribute("bookRecordsResult", service.getAllBookRecords());
                            } else {
                                service.createBook(values);
                                request.setAttribute("bookRecordsResult", service.getAllBookRecords());
                            }
                            break;
                        case "delete":
                            String[] checkValues = request.getParameterValues("boxes");
                            service.deleteRecords(Arrays.asList(checkValues));
                            request.setAttribute("bookRecordsResult", service.getAllBookRecords());
                            break;
                        default:
                            request.setAttribute("error", "Something Went Wrong!");
                    }
                } catch (SQLException | ClassNotFoundException | ParseException error) {
                    request.setAttribute("error", error.toString());
                }
            }

            try {
                request.setAttribute("bookRecordsResult", service.getAllBookRecords());
                resultPage = "book_authorRecords.jsp";
            } catch (SQLException | ClassNotFoundException | ParseException ex) {
                request.setAttribute("bookRecordsResult", ex.toString());
            }
        } catch (Exception e) {
            System.out.println("Did Not Work");
        }

        RequestDispatcher view = request.getRequestDispatcher(resultPage);
        view.forward(request, response);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    public DAO_Strategy injectWithDependancies() throws Exception, ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {

        SQL_Data_Provider dbDataProvider = null;
        Class dbClassData = Class.forName(sqlDataClass);

        DatabaseAccessorStrategy dbAccessorProvider = null;
        Class dbClass = Class.forName(dbStrategyClassName);

        DAO_Strategy daoProvider = null;
        Class daoClass = Class.forName(daoClassName);

        Constructor sqlDataConstructor = null;
        Constructor dbAccessConstructor = null;
        Constructor daoConstructor = null;

        try {
            sqlDataConstructor = dbClassData.getConstructor(new Class[]{
                String.class, String.class, String.class, String.class
            });

            dbAccessConstructor = dbClass.getConstructor(new Class[]{
                SQL_Data_Provider.class
            });

            daoConstructor = daoClass.getConstructor(new Class[]{
                DatabaseAccessorStrategy.class
            });
        } catch (NoSuchMethodException ex) {
            System.out.println(ex.toString());
        }

        if (sqlDataConstructor != null) {
            Object[] constructorArgs = new Object[]{
                driverClass, dbURL, dbUserName, password
            };
            dbDataProvider = (SQL_Data_Provider) sqlDataConstructor.newInstance(constructorArgs);
        }

        if (dbAccessConstructor != null) {
            Object[] accessorConArgs = new Object[]{
                dbDataProvider
            };
            dbAccessorProvider = (SQL_Accessor) dbAccessConstructor.newInstance(accessorConArgs);
        }

        if (daoConstructor != null) {
            Object[] daoConArgs = new Object[]{
                dbAccessorProvider
            };
            daoProvider = (DAO_Strategy) daoConstructor.newInstance(daoConArgs);
        }

        return (DAO_Strategy)daoProvider;
        
    }

    @Override
    public void init() throws ServletException {
        ctx = getServletContext();
        driverClass = (String) ctx.getInitParameter("db.driverName");
        dbURL = (String) ctx.getInitParameter("db.driver.url");
        dbUserName = (String) ctx.getInitParameter("db.userName");
        password = (String) ctx.getInitParameter("db.password");
        dbStrategyClassName = (String) ctx.getInitParameter("dbStrategy");
        sqlDataClass = (String) ctx.getInitParameter("mySQL_DataClass");
        daoClassName = (String) ctx.getInitParameter("daoClass");
    }

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
