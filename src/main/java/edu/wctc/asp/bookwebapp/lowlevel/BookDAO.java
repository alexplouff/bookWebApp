/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.lowlevel;

import edu.wctc.asp.bookwebapp.model.Author;
import edu.wctc.asp.bookwebapp.model.AuthorStrategy;
import edu.wctc.asp.bookwebapp.model.Book;
import edu.wctc.asp.bookwebapp.model.BookStrategy;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alex
 */
public class BookDAO {

    private DatabaseAccessorStrategy accessor;

    private static final String NULL_REPLACEMENT_VALUE = "Not Entered";
    private static final String DATABASE = "Library";

    private static final String BOOK_TABLE = "Book";
    private static final String BOOK_PRIMARY_KEY = "BookID";
    private static final String BOOK_TITLE_COLUMN = "title";
    private static final String DATE_PUBLISHED_COLUMN = "DatePublished";

    private static final String AUTHOR_TABLE = "Author";
    private static final String AUTHOR_PRIMARY_KEY = "AuthorID";
    private static final String AUTHOR_FIRST_NAME_COLUMN = "AuthorFirstName";
    private static final String AUTHOR_LAST_NAME_COLUMN = "AuthorLastName";

    private static List BOOK_COLUMNS;
    private static List AUTHOR_COLUMNS;

    public BookDAO(DatabaseAccessorStrategy accessor) {
        setAccessor(accessor);
        
        BOOK_COLUMNS = new ArrayList();
        BOOK_COLUMNS.add(BOOK_PRIMARY_KEY);
        BOOK_COLUMNS.add(BOOK_TITLE_COLUMN);
        BOOK_COLUMNS.add(DATE_PUBLISHED_COLUMN);
        BOOK_COLUMNS.add(AUTHOR_PRIMARY_KEY);

        AUTHOR_COLUMNS = new ArrayList();
        AUTHOR_COLUMNS.add(AUTHOR_PRIMARY_KEY);
        AUTHOR_COLUMNS.add(AUTHOR_FIRST_NAME_COLUMN);
        AUTHOR_COLUMNS.add(AUTHOR_LAST_NAME_COLUMN);
    }

    public DatabaseAccessorStrategy getAccessor() {
        return accessor;
    }

    public final void setAccessor(DatabaseAccessorStrategy accessor) throws IllegalArgumentException {
        if (accessor == null) {
            throw new IllegalArgumentException("Accessor Can Not Be Empty");
        } else {
            this.accessor = accessor;
        }
    }
    
    public List getAllRecords() throws Exception{
        List<Map<String,Object>> rawRecord =
                accessor.getJoinedRecords(DATABASE,BOOK_TABLE, BOOK_PRIMARY_KEY,
                                            AUTHOR_TABLE, AUTHOR_PRIMARY_KEY);   // "Author.AuthorID
        
        List<BookStrategy> bookList = new ArrayList<>(); 
        for(Map<String,Object> values : rawRecord){
            Object o = values.get("BookID");
            int bookID = (o == null) ? 00 : Integer.valueOf(o.toString());
            o = values.get("title");
            String title = (o == null) ? NULL_REPLACEMENT_VALUE : o.toString();
            o = values.get("DatePublished");
            String date = (o == null) ? NULL_REPLACEMENT_VALUE : o.toString();
            o = values.get(AUTHOR_PRIMARY_KEY);
            int authorID = (o == null) ? 00 : Integer.valueOf(o.toString());
            o = values.get(AUTHOR_FIRST_NAME_COLUMN);
            String firstName = (o == null) ? NULL_REPLACEMENT_VALUE: o.toString();
            o = values.get(AUTHOR_LAST_NAME_COLUMN);
            String lastName = (o == null) ? NULL_REPLACEMENT_VALUE : o.toString();
            
            BookStrategy book = new Book(bookID, title, date, new Author(authorID, firstName, lastName));
            bookList.add(book);
        }
        
        System.out.println("sucess");
        return bookList;
    }
    
    public void deleteRecords(List<Object> values) throws SQLException, ClassNotFoundException{
        accessor.deleteRecords(DATABASE, BOOK_TABLE, BOOK_PRIMARY_KEY, values);
    }
    
    public void createNewBook(BookStrategy book) throws SQLException, ClassNotFoundException{
        
        List values = new ArrayList();
        values.add(book.getTitle());
        values.add(book.getDatePublished());
        values.add(book.getAuthorID());
        BOOK_COLUMNS.remove(BOOK_PRIMARY_KEY);
        accessor.createRecord(DATABASE.concat(".".concat(BOOK_TABLE)), BOOK_COLUMNS, values);
       
    }
    
    public void createNewAuthor(Author author) throws SQLException, ClassNotFoundException{
        
        List values = new ArrayList();
        values.add(author.getFirstName());
        values.add(author.getLastName());
        AUTHOR_COLUMNS.remove(AUTHOR_PRIMARY_KEY);
        accessor.createRecord(DATABASE.concat(".".concat(BOOK_TABLE)), AUTHOR_COLUMNS, values);
       
    }
    
    public void updateBookByID(){
        
        
        accessor.updateRecord(table, BOOK_COLUMNS, values, pkc, pk);
    }
    
    
    public static void main(String[] args) throws Exception{
        SQL_Data_Provider data = new SQL_Data_Provider("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/Client?zeroDateTimeBehavior=convertToNull",
                "root", "root");
        SQL_Accessor accessor = new SQL_Accessor(data);
        BookDAO dao = new BookDAO(accessor);
        Book book = new Book();
        book.setTitle("Homage To Catalonia");
        book.setDatePublished("1938-04-25");
        book.setAuthorID("2");
      //  dao.deleteRecords(list);
        dao.createNewBook(book);
    }

}
