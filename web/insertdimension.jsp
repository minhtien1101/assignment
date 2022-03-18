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
            <table>
                <tr>
                    <td>Dimension: </td>
                    <td> <input type="text" name="name" placeholder="VD: 1220*2440*10mm" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
