<%-- 
    Document   : insertagency
    Created on : Mar 13, 2022, 11:02:10 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert New Agency</title>
    </head>
    <body>
        <h2>Insert New Agency</h2>
        <form action="../agency/insert" method="POST">
            Name: <input type="text" name="name"/>
            <br/>
            Phone:
            <input type="text" name="phone"/>
            <br/>
            Address:
            <input type="text" name="address"/>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
