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
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alex
 */

@Repository("authorService")
@Transactional(readOnly = true)
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
    
    public Author getAuthorAndBookCollectionById(Integer id){
        return authorRepo.findAuthorAndBookCollection(id);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteAuthors(Integer authorID){
        authorRepo.delete(authorID);
    }
    
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveAuthor(Author author){
        authorRepo.saveAndFlush(author);
    }
    
    
}
