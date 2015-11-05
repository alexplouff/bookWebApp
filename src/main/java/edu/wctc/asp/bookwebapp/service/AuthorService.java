/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.service;

import edu.wctc.asp.bookwebapp.entity.Author;
import edu.wctc.asp.bookwebapp.repository.AuthorRepository;
import edu.wctc.asp.bookwebapp.repository.BookRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author alex
 */
public class AuthorService {
    
    @Inject
    private AuthorRepository authorRepo;
    
    @Inject
    private BookRepository bookRepo;
    
    AuthorService(){
        
    }
    
    public List<Author> getAllAuthors(){
        return authorRepo.findAll();
    }
    
    public Author getAuthorByID(String ID){
        return authorRepo.findOne(Integer.valueOf(ID));
    }
    
    
}
