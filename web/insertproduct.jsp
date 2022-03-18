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
            <table>
                <tr>
                    <td>Name Product: </td>
                    <td><input type="text" name="name" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
