/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.service;

import edu.wctc.asp.bookwebapp.entity.Book;
import edu.wctc.asp.bookwebapp.repository.AuthorRepository;
import edu.wctc.asp.bookwebapp.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alex
 */
@Repository("bookService")
@Transactional(readOnly = true)
public class BookService {
    
    @Autowired
    private AuthorRepository authorRepo;
    
    @Autowired
    private BookRepository bookRepo;
    
    public BookService(){
    
    }
    
    public List<Book> findAllBooks(){
        return bookRepo.findAll();
    }
}
