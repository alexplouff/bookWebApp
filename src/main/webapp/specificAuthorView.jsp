<%-- 
    Document   : specificAuthorView
    Created on : Nov 10, 2015, 11:40:07 AM
    Author     : alex
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <table id="authorInfo">
            <tr>
                <td>Author ID: </td>
                <td>${author.authorID}</td>
            </tr>
            <tr>
                <td>First Name: </td>
                <td>${author.authorFirstName}</td>
            </tr>
            <tr>
                <td>Last Name: </td>
                <td>${author.authorLastName}</td>
            </tr>
            
        </table>
            
            <table id="books">
                
                <thead>
                <th>Book ID</th>
                <th>Title</th>
                <th>Date Published</th
                </thead>
                
                <c:forEach var="book" items="${author.bookCollection}">
                    <tr>
                        <td>${book.bookID}</td>
                        <td>${book.title}</td>
                        <td>${book.datePublished}</td>
                    </tr>
                </c:forEach>
                
            </table>
            
            
            
           
    </body>
</html>
