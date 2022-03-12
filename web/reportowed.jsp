<%-- 
    Document   : reportowed
    Created on : Mar 10, 2022, 4:34:54 PM
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
                <div class="header-title">Quản Lý Nhập Vật Liệu</div>

                <ul class="header-menu">
                    <li id="btn-search"><a href="home">Home</a></li>
                    <li id="btn-insert"><a href="insert">Insert</a></li>
                    <li id="btn-owed"><a href="owed">Report Owed</a></li>
                    <li id="btn-depot"><a href="#">Depot</a></li>
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
                        <div>From <input type="date" value="${requestScope.dateFrom}" name="dateFrom"></div>
                        <div>To <input type="date" value="${requestScope.dateTo}" name="dateTo"></div>
                        <input type="submit" value="Search">
                    </form>
                    <table>
                        <tr>
                            <td>Name Buyer</td>
                            <td>Name Product</td>
                            <td>Dimension</td>
                            <td>Date</td>
                            <td>Price</td>
                            <td>Quantity</td>
                            <td>Amount</td>
                            <td>Paid</td>
                            <td>Owed</td>
                            <td>Name Agent</td>
                            <td>UserName</td>
                        </tr>
                        <c:forEach items="${requestScope.invoicesDetailOwed}" var="i">
                            <tr>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                                <td>something</td>
                            </tr>
                        </c:forEach>
                    </table>
                        <div class="page"><input id="pageIndex" type="text" value="${requestScope.pageIndex}" onkeyup="keyUp(event)">/10</div>
                </div>
            </div>
        </div>
        <script>
            function keyUp(event) {
                if (event.keyCode === 13) {
                    window.location.href = "owed?page=" + document.getElementById("pageIndex").value;
                }
            }
        </script>
    </body>
</html>

