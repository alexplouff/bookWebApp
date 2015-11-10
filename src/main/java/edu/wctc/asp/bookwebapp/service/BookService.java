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
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alex
 */
@Repository("bookService")
@Transactional(readOnly = true)
public class BookService {
    
    @Inject
    private AuthorRepository authorRepo;
    
    @Inject
    private BookRepository bookRepo;
    
    public BookService(){
    
    }
    
    public List<Book> findAllBooks(){
        return bookRepo.findAll();
    }
    
    public Book getBookByID(Integer id){
        return bookRepo.findOne(id);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveBook(Book book){
        bookRepo.save(book);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteBook(Integer id){
        bookRepo.delete(id);
        bookRepo.flush();
    }
}
