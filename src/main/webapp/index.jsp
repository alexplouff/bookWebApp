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
        
        
     <div id="bookContainer">
                <table id="bookTable" style="text-align: center;">

                    <thead>
                    <th> Book ID </th>
                    <th> Title </th>
                    <th> Date Published </th>
                    <th> Delete </th>
                    <th> View</th>
                    </thead>
                    <tbody id="bookTableBody">
                        
                    </tbody>

                </table>
            
        </div>
        
         <div id="authorContainer">
                <table id="authorTable" style="text-align: center;">

                    <thead>
                    <th> Author ID </th>
                    <th> First Name </th>
                    <th> Last Name </th>
                    <th> Delete </th>
                    <th View</th>
                    </thead>
                    <tbody id="authorTableBody">

                    </tbody>

                </table>
            
        </div>
            
            
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script type="text/javascript" src="js.js"></script>
        
        
    </body>
</html>
