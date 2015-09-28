/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.lowlevel;

import edu.wctc.asp.bookwebapp.model.Author;
import edu.wctc.asp.bookwebapp.model.AuthorStrategy;
import edu.wctc.asp.bookwebapp.model.BookStrategy;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author alex
 */
public interface DAO_Strategy {

    public abstract void createNewAuthor(Author author) throws SQLException, ClassNotFoundException;

    public abstract void createNewBook(BookStrategy book) throws SQLException, ClassNotFoundException;

    public abstract void deleteRecords(List<Object> values) throws SQLException, ClassNotFoundException;

    public abstract List getAllRecords() throws Exception;

    public abstract void updateAuthorByID(AuthorStrategy author) throws SQLException, ClassNotFoundException;

    public abstract void updateBookByID(BookStrategy book) throws SQLException, ClassNotFoundException;
    
}
