<%-- 
    Document   : login
    Created on : Feb 19, 2022, 9:03:31 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>Login</title>
    </head>
    <body>
        <div id="container">
            <h1>Login</h1>
            <span class="msg error"></span>
            <form id="form" action="login" method="POST">
                <div class="form-control">
                    <input id="username" name="username" type="text" placeholder="UserName"/>
                </div>
                <div class="form-control">
                    <input id="password" name="password" type="password" placeholder="Password"/>
                </div>

                <div class="form-submit">
                    <input type="submit" value="Login"/>
                </div>
                <div class="sign-up">Not a account? <a href="signup">Sign Up</a></div>
            </form>
        </div>
    </body>
</html>
