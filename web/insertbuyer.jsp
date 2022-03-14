<%-- 
    Document   : insertbuyer
    Created on : Mar 13, 2022, 9:24:02 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Insert New Buyer</h2>
        <form action="../buyer/insert" method="POST">
            Name <input type="text" name="name" value="${param.name}"required />
            <br/>
            Phone <input type="text" name="phone" required/>
            <br/>
            Gender <input type="radio" name="gender" value="male" required/> Male
            <input type="radio" name="gender" value="female" required/> Female
            <br/>
            <input type="date" name="dob" required/>
            <br/>
            <input type="text" name="address" required/>
            <br/>
            <button type="button" onclick="window.history.go(-1);"></button> <input type="submit" value="Save"/>        
        </form>
    </body>
</html>
