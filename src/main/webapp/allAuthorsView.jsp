<%-- 
    Document   : allAuthorsView
    Created on : Nov 5, 2015, 11:58:50 AM
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
        ${error}
        <div id="tableDataContainer">
            <form id="authorDeleteForm" action="AuthorController?action=delete" method="POST">
                <table id="tableData" style="text-align: center;">

                    <thead>
                    <th> Author ID </th>
                    <th> First Name </th>
                    <th> Last Name </th>
                    <th> Delete </th>
                    <th View</th>
                    </thead>
                    <tbody>
                        <c:forEach var="author" items="${authorList}" >

                            <tr>
                                <td>${author.authorID}</td>
                                <td>${author.authorFirstName}</td>
                                <td>${author.authorLastName}</td>
                                <td><input type="checkbox" value="${author.authorID}" name="boxes"/></td>
                                <td><a href="AuthorController?action=viewAuthor&id=${author.authorID}"> View </a></td>
                            </tr>

                        </c:forEach>
                        <tr>
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

        <sec:authorize access="hasAnyRole('ROLE_MGR')">
            <div id="authorAddEditFormContainer">

                <form id="authorAddEditForm" action="AuthorController?action=save" method="POST">

                    <table>
                        <tr>
                            <td>Author ID </td>
                            <td><input type="text" id="authorID" name="authorID" placeholder="Author ID" readonly/></td>
                        </tr>
                        <tr>
                            <td>First Name</td>
                            <td><input type="text" id="firstName" name="firstName" placeholder="First Name" /></td>
                        </tr>
                        <tr>
                            <td>Last Name</td>
                            <td><input type="text" id="lastName" name="lastName" placeholder="Last Name" /></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><button type="submit" name="authorFormSubmitBtn" id="authorFormSubmitBtn">Submit</button> </td>
                        </tr>
                    </table>


                </form>

            </div>
        </sec:authorize>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript" src="js.js"></script>
    </body>
</html>
