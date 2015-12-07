<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

    <head>
        <title> Book - Author Splash</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="splash">

        <div id="masterContainer">
           
            
            <a href='<%= this.getServletContext().getContextPath() + "/j_spring_security_logout"%>'>Log Me Out</a>
        
            
            <div id="header">
                <ul>
                    <li><a href="#bookContainer">Books</a></li>
                    <li><a href="#authorContainer">Authors</a></li>
                    <li><a href="">Search Authors by Name</a></li>
                    <li><a href="">Search Books by Title</a></li>
                </ul>
            </div>

            <div id="bookContainer">
                <form id="bookTableForm" name="bookTableForm" method="POST" >
                <table id="bookTable" style="text-align: center;">

                    <thead>
                    <th> Book ID </th>
                    <th> Title </th>
                    <th> Date Published </th>
                    <th> Author ID </th>
                    </thead>
                    <tbody id="bookTableBody">

                    </tbody>
                </table>
                </form>
                <button id="bookDeleteBtn" name="bookDeleteBtn" type="button">Delete</button>
                <sec:authorize access="hasAnyRole('ROLE_MGR')">
                <form id="bookForm" name="bookForm" action="BookController?action=save" method="POST" >
                    <table>
                        <tr>
                            <td>Book ID: </td>
                            <td><input type="text" id="bookID" name="bookID" placeholder="Book ID" readonly /></td>
                        </tr>
                        <tr>
                            <td>Title: </td>
                            <td><input type="text" id="title" name="title" placeholder="Title"  /></td>
                        </tr>
                        <tr>
                            <td>Date Published: </td>
                            <td><input type="text" id="datePublished" name="datePublished" placeholder="Date Published" /></td>
                        </tr>
                        <tr>
                            <td>Author ID: </td>
                            <td><input type="text" id="authorID" name="authorID" placeholder="Author ID" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button type="button" id="bookSubmitBtn" name="bookSubmitBtn">Save</button></td>
                        </tr>
                    </table>
                </form>

            </div>
                </sec:authorize>

            <div id="authorContainer">
                <table id="authorTable" style="text-align: center;">

                    <thead>
                    <th> Author ID </th>
                    <th> First Name </th>
                    <th> Last Name </th>
                    <th> Delete </th>
                    </thead>
                    <tbody id="authorTableBody">

                    </tbody>

                </table>

            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript" src="js.js"></script>


    </body>
</html>
