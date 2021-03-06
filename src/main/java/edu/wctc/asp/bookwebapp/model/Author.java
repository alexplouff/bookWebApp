/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asp.bookwebapp.model;

/**
 *
 * @author alex
 */
public class Author implements AuthorStrategy {

    private String firstName, lastName;
    private int id;
    
    public Author () {};

    public Author(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
    
    public Author(int id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Author(String id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public final void setFirstName(String firstName) throws IllegalArgumentException {
//        if (this.firstName == null) {
//             throw new IllegalArgumentException("First Name Can Not Be Empty");
//        } else {
            this.firstName = firstName;
      //  }
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public final void setLastName(String lastName) throws IllegalArgumentException{
//        if (this.lastName == null) {
//            throw new IllegalArgumentException("Last Name Can Not Be Empty");
//        } else {
            this.lastName = lastName;
     //   }
    }

    @Override
    public int getId() {
        return id;
    }

    public final void setId(String id) {
        if (id.matches("\\d+")) {
            this.id = Integer.valueOf(id);
        } else {
            throw new IllegalArgumentException("Digits Only");
                }
    }

    public final void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID Must Be > 0");
        } else {
            this.id = id;
        }
    }
    
    @Override
    public String toString(){
        StringBuilder sBuffer = new StringBuilder("\nAuthor ID: ");
        sBuffer.append(id)
                .append("\nFirst Name: ").append(firstName)
                .append(" Last Name: ").append(lastName);
        return sBuffer.toString();
    }

}
