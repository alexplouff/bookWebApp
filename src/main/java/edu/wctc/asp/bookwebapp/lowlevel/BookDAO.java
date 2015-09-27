/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.lowlevel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author alex
 */
public class BookDAO {

    private DatabaseAccessorStrategy accessor;

    private static final String DATABASE = "Library.";

    private static final String BOOK_TABLE = "Book";
    private static final String BOOK_PRIMARY_KEY = "BookID";
    private static final String BOOK_TITLE_COLUMN = "title";
    private static final String DATE_PUBLISHED_COLUMN = "DatePublished";

    private static final String AUTHOR_TABLE = "Author";
    private static final String AUTHOR_PRIMARY_KEY = "AuthorID";
    private static final String AUTHOR_FIRST_NAME_COLUMN = "FirstName";
    private static final String AUTHOR_LAST_NAME_COLUMN = "LastName";

    private static List BOOK_COLUMN;
    private static List AUTHOR_COLUMNS;

    public BookDAO(DatabaseAccessorStrategy accessor) {
        setAccessor(accessor);
        
        BOOK_COLUMN = new ArrayList();
        BOOK_COLUMN.add(BOOK_PRIMARY_KEY);
        BOOK_COLUMN.add(BOOK_TITLE_COLUMN);
        BOOK_COLUMN.add(DATE_PUBLISHED_COLUMN);
        BOOK_COLUMN.add(AUTHOR_PRIMARY_KEY);

        AUTHOR_COLUMNS = new ArrayList();
        AUTHOR_COLUMNS.add(AUTHOR_PRIMARY_KEY);
        AUTHOR_COLUMNS.add(AUTHOR_FIRST_NAME_COLUMN);
        AUTHOR_COLUMNS.add(AUTHOR_LAST_NAME_COLUMN);
    }

    public DatabaseAccessorStrategy getAccessor() {
        return accessor;
    }

    public void setAccessor(DatabaseAccessorStrategy accessor) throws IllegalArgumentException {
        if (accessor == null) {
            throw new IllegalArgumentException("Accessor Can Not Be Empty");
        } else {
            this.accessor = accessor;
        }
    }
    
    public List getAllRecords() throws Exception{
        List<Map<String,Object>> rawRecord = accessor.getRecords(DATABASE+BOOK_TABLE);
        
        
        
        
        return rawRecord;
    }

}
