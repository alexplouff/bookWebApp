/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.model;

import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author alex
 */
public class BookAuthorDTO {
    
    private int bookID;
    private String title;
    private int authorID;
    private String firstName,lastName;
    private Date datePublished;
    
    private BookStrategy book;
    private AuthorStrategy author;
    
    public BookAuthorDTO(int bookID, String title, Date date, int authorID, String firstName, String lastName) throws ParseException{
        setBookID(bookID);
        setTitle(title);
        setDate(date);
        setAuthorID(authorID);
        setFirstName(firstName);
        setLastName(lastName);
        
        book = new Book(bookID, title, date, authorID);
        author = new Author(firstName, lastName);
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDate(Date date) {
        this.datePublished = date;
    }
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.bookID;
        hash = 53 * hash + this.authorID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BookAuthorDTO other = (BookAuthorDTO) obj;
        if (this.bookID != other.bookID) {
            return false;
        }
        if (this.authorID != other.authorID) {
            return false;
        }
        return true;
    }

    public BookAuthorDTO(){}
    
    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BookStrategy getBook() {
        return book;
    }

    public void setBook(BookStrategy book) {
        this.book = book;
    }

    public AuthorStrategy getAuthor() {
        return author;
    }

    public void setAuthor(AuthorStrategy author) {
        this.author = author;
    }
    
}
