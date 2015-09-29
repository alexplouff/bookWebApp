<%-- 
    Document   : book_authorRecords
    Created on : Sep 29, 2015, 11:09:56 AM
    Author     : alex
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Book - Author Records </title>
    </head>
    <body>
        
        <table style="text-align: center;">
            
            <thead>
                <th>Book ID</th>
                <th>Title</th>
                <th>Date Published</th>
                <th>Author ID</th>
                <th>Author First Name</th>
                <th>Author Last Name</th>
            </thead>
            
            <tbody>
            <c:forEach var="record" items="${bookRecordsResult}">
            
                <tr>
                    <td>${record.bookID}</td>
                    <td>${record.title}</td>
                    <td>${record.datePublished}</td>
                    <td>${record.author.id}</td>
                    <td>${record.author.firstName}</td>
                    <td>${record.author.lastName}</td>
                </tr>
            
            
            </c:forEach>
            </tbody>
            
            
        </table>
        
        
        
    </body>
</html>
