<%-- 
    Document   : reportowed
    Created on : Mar 10, 2022, 4:34:54 PM
    Author     : DELL
--%>

<%-- 
    Document   : home
    Created on : Mar 5, 2022, 12:28:18 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <title>Report Owed</title>
    </head>
    <body>
        <div class="container">
            <!-- header -->
            <div class="header">
                <div class="header-title">Quản Lý Nhập Vật Liệu SX Nội Thất</div>

                <ul class="header-menu">
                    <li id="btn-search"><a href="home">Home</a></li>
                    <li id="btn-insert"><a href="insert">Insert</a></li>
                    <li id="btn-owed"><a href="owed">Report Owed</a></li>
                </ul>

                <div class="user-info">
                    <div class="user-name">
                        <i class="fa-solid fa-user"></i>
                        <span>Username</span>
                        <i class="fa-solid fa-caret-down"></i>

                    </div>
                    <ul class="user-menu">
                        <li><a href="login">Log Out</a></li>
                    </ul>
                </div>
            </div>
            <!-- body -->
            <div class="content">               
                <!-- report owed -->
                <div id="owed" class="owed">
                    <form action="owed" method="get">
                        <div>From <input type="date" name="dateFrom"></div>
                        <div>To <input type="date" name="dateTo"></div>
                        <input type="submit" value="Search">
                    </form>
                    <table>
                        <tr>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                        </tr>
                        <tr>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                            <td>something</td>
                        </tr>
                    </table>
                    <div>Total Amount Owed: </div>
                    <div class="page"><input type="text" value="1" onkeyup="keyUp(e)">/10</div>
                </div>
            </div>
        </div>
        
    </body>
</html>

