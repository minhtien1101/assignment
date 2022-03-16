<%-- 
    Document   : insertdimension
    Created on : Mar 13, 2022, 11:01:35 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert New Dimension Product</title>
    </head>
    <body>
        <h2>Insert New Dimension</h2>
        <form action="../dimension/insert" method="POST">
            Name: <input type="text" name="name" placeholder="1220*2440*10mm"/>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
