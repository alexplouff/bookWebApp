<%-- 
    Document   : book_authorRecords
    Created on : Sep 29, 2015, 11:09:56 AM
    Author     : alex
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:set var="error" value="${error}" scope="request"/>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Book - Author Records </title>
        <link href="css.css" type="text/css" rel="stylesheet" />
    </head>
    <body>

        <div id="pageContainer">

            <div id="login" >
                <c:if test="${not empty user}">
                    <p>Logged In As: (${user})
                    <a href="bookAuthorControls?loginAction=logout">Log Out</a></p>
                </c:if>
            </div>
            
            <div id="errorMessage" >
                <p id="error">${error}</p>
            </div>

                <div id="bookTableContainer" >
                <form id="deleteForm" method="POST" action="bookAuthorControls?action=delete" >
                    <table id="recordTable" style="text-align: center;">

                        <thead>   
                        <th>Book ID</th>
                        <th>Title</th>
                        <th>Date Published</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Author ID</th> 
                        <th>Delete</th>
                        </thead>

                        <tbody>
                            <c:forEach var="record" items="${bookRecordsResult}">
                                <tr>
                                    <td class="selectable"><a>${record.bookID}</a></td>
                                    <td>${record.title}</td>
                                    <td>${record.datePublished}</td>
                                    <td>${record.author.firstName}</td>
                                    <td>${record.author.lastName}</td>
                                    <td>${record.authorID}</td>
                                    <td> <input type="checkbox" name="boxes" class="boxes" value="${record.bookID}" /> </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td><button type="submit" id="deleteButton" name="submit" value="delete">Delete</button></td>
                            </tr>

                        </tbody>
                    </table>
                </form>
            </div>

            <div id="textInputs">
                <form id="add_editForm" method="POST" action="bookAuthorControls?action=save">
                    <table>
                        <tr>
                            <td>Book ID</td>
                            <td><input type="text" id="bookID" name="bookID" placeholder="Book ID" readonly="true" /></td>
                        </tr>
                        <tr>
                            <td>Title</td>
                            <td><input type="text" id="title" name="title" placeholder="Title" /></td>
                        </tr>
                        <tr>
                            <td>Date Published</td>
                            <td><input type="text" id="datePublished" name="datePublished" placeholder="Date" /></td>
                        </tr>
                        <tr>
                            <td>First Name</td>
                            <td><input type="text" id="authorFirstName" name="authorFirstName" placeholder="First Name" /></td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td><input type="text" id="authorLastName" name="authorLastName" placeholder="Last Name" /></td>
                        </tr>
                        <tr>
                            <td>Author ID</td>
                            <td><input type="text" id="authorID" name="authorID" placeholder="Author ID" /></td>
                        </tr>
                        <tr>
                            <td><button type="submit" id="submit" name="submit">Submit</button></td>
                            <td><button type="button" id="clearButton">Clear</button>
                        </tr>
                    </table>
                </form>
            </div>

        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="js.js" type="text/javascript" ></script>
    </body>
</html>