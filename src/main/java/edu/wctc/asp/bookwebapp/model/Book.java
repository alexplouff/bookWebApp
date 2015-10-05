/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 *
 * @author alex
 */
public class Book implements BookStrategy {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DIGIT_REG_EX = "\\d+";
    private static final DateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    private int bookID, authorID;
    private String title;
    private LocalDate datePublished;
    private AuthorStrategy author;

    public Book() {
    }

    public Book(String title, String datePublished, String authorID) throws ParseException { //Service Create
        setTitle(title);
        setDatePublished(datePublished);
        setAuthorID(authorID);
    }

    public Book(String bookID, String title, String datePublished, String authorID) throws ParseException { //Service Update
        setBookID(bookID);
        setTitle(title);
        setDatePublished(datePublished);
        setAuthorID(authorID);
    }

    public Book(int bookID, String title, String datePublished, int authorID, Author author) throws ParseException {
        setBookID(bookID);
        setTitle(title);
        setDatePublished(datePublished);
        setAuthorID(authorID);
        setAuthor(author);
    }

    @Override
    public int getBookID() {
        return bookID;
    }

    public final void setBookID(String bookID) throws IllegalArgumentException {
        if (bookID.matches(DIGIT_REG_EX)) {
            this.bookID = Integer.valueOf(bookID);
        } else {
            throw new IllegalArgumentException("Digits Only");
        }
    }

    public final void setBookID(int bookID) throws IllegalArgumentException {
        if (bookID < 0) {
            throw new IllegalArgumentException("ID Must Be > 0");
        }
        this.bookID = bookID;
    }

    @Override
    public int getAuthorID() {
        return authorID;
    }

    public final void setAuthorID(String authorID) throws IllegalArgumentException {
        if (authorID.matches(DIGIT_REG_EX)) {
            this.authorID = Integer.valueOf(authorID);
        } else {
            throw new IllegalArgumentException("Digits Only");
        }
    }

    public final void setAuthorID(int authorID) throws IllegalArgumentException {
        if (authorID < 0) {
            throw new IllegalArgumentException("Must Be > 0");
        } else {
            this.authorID = authorID;
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    public final void setTitle(String title) throws IllegalArgumentException {
        if (title.length() > 1) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title Must Be Greater Than 1 Character;");
        }
    }

    @Override
    public LocalDate getDatePublished() {
        return datePublished;
    }

    public final void setDatePublished(String datePublished) throws IllegalArgumentException, ParseException {
        if (datePublished == null) {
            throw new IllegalArgumentException("Invalid Date/Format");
        } else {
            Date sqlDate = SDF.parse(datePublished);
            Instant instant = sqlDate.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            this.datePublished = LocalDate.parse(zdt.toLocalDate().toString());
        }
    }

    @Override
    public AuthorStrategy getAuthor() {
        return author;
    }

    public final void setAuthor(AuthorStrategy author) {
        if (author != null) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author Can Not Be Empty");

        }
    }

    @Override
    public String toString() {
        StringBuffer sBuffer = new StringBuffer("BookID: ").append(bookID);
        sBuffer.append("\nTitle: ").append(title)
                .append("\nDate Published: ").append(datePublished).append("\n")
                .append(author);
        return sBuffer.toString();
    }
}
