/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.bookservice;

import edu.wctc.asp.bookwebapp.lowlevel.BookDAO;
import edu.wctc.asp.bookwebapp.lowlevel.DAO_Strategy;
import edu.wctc.asp.bookwebapp.lowlevel.SQL_Accessor;
import edu.wctc.asp.bookwebapp.lowlevel.SQL_Data_Provider;
import edu.wctc.asp.bookwebapp.model.Author;
import edu.wctc.asp.bookwebapp.model.Book;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author alex
 */
public class BookService {

    private DAO_Strategy dao;

    public BookService(DAO_Strategy dao) {
        setDAO(dao);
    }

    public final void setDAO(DAO_Strategy dao) {
        this.dao = dao;
    }

    public DAO_Strategy getDAO() {
        return dao;
    }
    
    public void createBook(List values) throws SQLException, ParseException, ClassNotFoundException{
        dao.createNewBook(
                new Book(values.get(0).toString(), values.get(1).toString(), values.get(2).toString()));
    }

    public void updateBookByID(List values) throws SQLException, ClassNotFoundException, ParseException {
        dao.updateBookByID(
                new Book(values.get(0).toString(), values.get(1).toString(), values.get(2).toString(), values.get(3).toString()));
    }

    public void updateAuthorByID(List values) throws SQLException, ClassNotFoundException, ParseException {
        dao.updateAuthorByID(
                new Author(values.get(0).toString(), values.get(1).toString(), values.get(2).toString()));
    }

    public void deleteRecords(List values) throws SQLException, ClassNotFoundException {
        dao.deleteRecords(values);
    }

    public List getAllBookRecords() throws SQLException, ClassNotFoundException, ParseException {
        return dao.getAllRecords();
    }

    public static void main(String[] args) throws Exception {
        SQL_Data_Provider data = new SQL_Data_Provider("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/Client?zeroDateTimeBehavior=convertToNull",
                "root", "root");
        SQL_Accessor accessor = new SQL_Accessor(data);
        BookDAO dao = new BookDAO(accessor);
        BookService service = new BookService(dao);
//        List list = new ArrayList();
//        list.add(1);
//        list.add("War and Peace");
//        list.add("1869-05-22");
//        list.add(1);
//        service.updateBookByID(list);
        System.out.println(service.getAllBookRecords());
    }
}
