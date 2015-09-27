/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.model;

import java.util.Date;

/**
 *
 * @author alex
 */
public interface BookStrategy {

    public abstract AuthorStrategy getAuthor();

    public abstract int getAuthorID();

    public abstract int getBookID();

    public abstract Date getDatePublished();

    public abstract String getTitle();
    
}
