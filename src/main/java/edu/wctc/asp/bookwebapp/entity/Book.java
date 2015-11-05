/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author alex
 */
@Entity
@Table(name = "Book")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b"),
    @NamedQuery(name = "Book.findByBookID", query = "SELECT b FROM Book b WHERE b.bookID = :bookID"),
    @NamedQuery(name = "Book.findByTitle", query = "SELECT b FROM Book b WHERE b.title = :title"),
    @NamedQuery(name = "Book.findByDatePublished", query = "SELECT b FROM Book b WHERE b.datePublished = :datePublished")})
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BookID")
    private Integer bookID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title")
    private String title;
    @Column(name = "DatePublished")
    @Temporal(TemporalType.DATE)
    private Date datePublished;
    @JoinColumn(name = "AuthorID", referencedColumnName = "AuthorID")
    @ManyToOne(cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private Author authorID;

    public Book() {
    }

    public Book(Integer bookID) {
        this.bookID = bookID;
    }

    public Book(Integer bookID, String title) {
        this.bookID = bookID;
        this.title = title;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Author getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Author authorID) {
        this.authorID = authorID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bookID != null ? bookID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Book)) {
            return false;
        }
        Book other = (Book) object;
        if ((this.bookID == null && other.bookID != null) || (this.bookID != null && !this.bookID.equals(other.bookID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.wctc.asp.bookwebapp.bookservice.Book[ bookID=" + bookID + " ]";
    }
    
}
