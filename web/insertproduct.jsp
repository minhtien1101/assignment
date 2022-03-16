<%-- 
    Document   : insertproduct
    Created on : Mar 13, 2022, 9:52:17 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert New Product</title>
    </head>
    <body>
        <h2>Insert New Product</h2>
        <form action="../product/insert" method="POST">
            Name: <input type="text" name="name"/>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
