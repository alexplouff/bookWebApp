<%-- 
    Document   : book_authorRecords
    Created on : Sep 29, 2015, 11:09:56 AM
    Author     : alex
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="error" value="${error}" scope="request"/>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Book - Author Records </title>
    </head>
    <body>

        <div id="pageContainer">
            
            <div id="errorMessage" >
                <p id="error">${error}</p>
            </div>
            
            <div id="authorTable" >
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
                                <td>${record.author.firstName}</td>
                                <td>${record.author.lastName}</td>
                                <td>${record.author.id}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div id="add_updateForm">
                <form id="add_updateForm" method="POST" action="bookAuthorControls"> 
                    <input type="text" id="title" name="title" placeholder="Title" />
                    <input type="text" id="datePublished" name="datePublished" placeholder="Date" />
                    <input type="text" id="authorID" name="authorID" placeholder="Author ID" />
                    <input type="text" id="authorFirstName" name="authorFirstName" placeholder="First Name" />
                    <input type="text" id="authorLastName" name="authorLastName" placeholder="Last Name" />
                    <button type="submit" id="submit" name="submit" value="Submit"/>
                </form>
            </div>



        </div>

    </body>
</html>
