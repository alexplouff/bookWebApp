<%-- 
    Document   : allBooksView
    Created on : Oct 29, 2015, 1:11:01 PM
    Author     : alex
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <div id="tableData">
            <form id="bookDeleteForm" action="BookController?action=delete" method="POST">
                <table style="text-align: center;">

                    <thead>
                    <th> Book ID </th>
                    <th> Title </th>
                    <th> Date Published</th>
                    <th> Author First Name</th>
                    <th> Author Last Name</th>
                    <th> Author ID</th>
                    </thead>
                    <tbody>
                        <c:forEach var="book" items="${bookList}" >

                            <tr>
                                <td>${book.bookID}</td>
                                <td>${book.title}</td>
                                <td>${book.datePublished}</td>
                                <td>${book.authorID.authorFirstName}</td>
                                <td>${book.authorID.authorLastName}</td>
                                <td>${book.authorID.authorID}</td>
                                <td><input type="checkbox" value="${book.bookID}" name="boxes"/></td>
                            </tr>

                        </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><button type="submit" id="deleteSubmitBtn">Delete</button></td>
                            </tr>
                    </tbody>

                </table>
            </form>

        </div>

        <div id="bookAddEditFormContainer">

            <form id="bookAddEditForm" action="BookController?action=save" method="POST">

                <table>
                    <tr>
                        <td>Book ID </td>
                        <td><input type="text" id="bookID" name="bookID" placeholder="Book ID" readonly/></td>
                    </tr>
                    <tr>
                        <td>Title</td>
                        <td><input type="text" id="title" name="title" placeholder="Title" /></td>
                    </tr>
                    <tr>
                        <td>Date Published</td>
                        <td><input type="text" id="datePublished" name="datePublished" placeholder="Date Published" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><button type="submit" name="bookFormSubmitBtn" id="bookFormSubmitBtn">Submit</button> </td>
                    </tr>
                </table>


            </form>

        </div>
    </body>
</html>
