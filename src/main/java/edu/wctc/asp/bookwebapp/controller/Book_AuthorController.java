/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.controller;

import edu.wctc.asp.bookwebapp.entity.Author;
import edu.wctc.asp.bookwebapp.entity.Book;

import edu.wctc.asp.bookwebapp.service.AbstractFacade;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "Book_AuthorController", urlPatterns = {"/bookAuthorControls"})
public class Book_AuthorController extends HttpServlet {

    private final static String ERROR_PAGE = "errorPage.jsp";
    private final static String LOGIN_ERROR_MESSAGE = "Was Not Able To Log You In";
    private String resultPage;

    @Inject
    private AbstractFacade<Author> authorService;

    @Inject
    private AbstractFacade<Book> bookService;

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
                            Book book = null;
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date date;

                            String bookAuthorID = request.getParameter("authorID");
                            String bookID = request.getParameter("bookID");
                            String title = request.getParameter("title");
                            String userEnteredDate = request.getParameter("datePublished");
                            Author author = null;

                            if (bookID.matches("\\d+")) {            // Update

                                book = bookService.find(new Integer(bookID));
                                book.setTitle(title);
                                book.setDatePublished(sdf.parse(userEnteredDate));
                                
                                author = authorService.find(new Integer(bookAuthorID));
                                author.setAuthorFirstName(request.getParameter("authorFirstName"));
                                author.setAuthorLastName(request.getParameter("authorLastName"));
                                book.setAuthorID(author);
                                
                                bookService.edit(book);
                                
                                
                            } else {    
                                author = new Author();
                                author.setAuthorFirstName(request.getParameter("authorFirstName"));
                                author.setAuthorLastName(request.getParameter("authorLastName"));
                                authorService.create(author);
//                                List<Author> authorList = (List<Author>)authorService.findAll();
//                                author = authorList.get(authorList.size()-1);
                                
                                Collection<Book> list = new ArrayList<>();
                                book = new Book(0);
                                book.setTitle(title);
                                book.setDatePublished(sdf.parse(userEnteredDate));
                                book.setAuthorID(author);
                                list.add(book);
                                author.setBookCollection(list);
                                //authorService.flush();
                                authorService.edit(author);
                            }
                            break;
                        case "delete":
                            String[] checkValues = request.getParameterValues("boxes");
                            for (String id : checkValues) {
                                book = bookService.find(new Integer(id));
                                bookService.remove(book);
                            }
                            break;
                        default:
                            request.setAttribute("error", "Something Went Wrong!");
                    }
                } catch (Exception error) {
                    request.setAttribute("error", error.toString());
                }
            }

            try {
                request.setAttribute("bookRecordsResult", bookService.findAll());
                resultPage = "book_authorRecords.jsp";

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                request.setAttribute("bookRecordsResult", e.toString());
            }

        } catch (Exception e) {
            request.setAttribute("error", "Did Not Work");
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


/*
    public List<Author> findByName(String name){
     import Query
        String jpql = "Select A from Author Where A.authorName = ?1";
        Query query = getEntityManager().createQuery(String jqplString);
        query.setParameter(1, name);
        return query.getResultList();

    }
*/