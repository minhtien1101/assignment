<%-- 
    Document   : signin
    Created on : Feb 19, 2022, 9:04:13 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="style/style.css" rel="stylesheet" type="text/css"/>
        <title>Sign Up</title>
    </head>
    <body>
        <div id="container">
            <h1>Sign Up</h1>
            <form id="form" action="signup">
                <div class="form-control">
                    <input id="username" type="text" name="fullname" placeholder="Fullname">
                </div>
                <div class="form-control">
                    <input id="username" type="text" name="username" placeholder="Username">
                </div>
                <div class="form-control">
                    <input id="password" type="password" name="password" placeholder="Password">
                </div>
                <div class="form-control">
                    <input id="password" type="password" name="re-password" placeholder="Re-password">
                </div>
                <div class="form-submit">
                    <input type="submit" value="Sign Up"/>
                </div>
            </form>
            <div class="sign-up">Have a account? <a href="login">Sign In</a></div>
        </div>
    </body>
</html>
