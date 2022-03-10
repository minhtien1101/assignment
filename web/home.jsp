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
        <title>Home</title>
    </head>
    <body>
        <div class="container">
            <!-- header -->
            <div class="header">
                <div class="header-title">Quản lý nhập vật liệu</div>

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
                <!-- search -->
                <div id="search" class="search">
                    <form action="home" method="GET">
                        <div>From <input type="date" name="dateFrom"></div>
                        <div>To <input type="date" name="dateTo"></div>
                        <input type="submit" value="Search">
                    </form>
                    <a href="home?dateFrom=&dateTo=">View all</a>
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
                            <td>something</td>
                            <td>something</td>
                            
                        </tr>

                        <c:forEach items="${requestScope.detailInvoices}" var="d">
                            <tr>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                <td>${d.invoice.date}</td>
                                
                            </tr>
                        </c:forEach>
                    </table>
                    <div class="page"><input type="text" value="1" onkeyup="keyUp(e)">/10</div>
                </div>

                <!-- insert -->
<!--                <div id="insert" class="insert">
                    <form action="insert" method="POST">
                        <div>Buyer<input type="text"></div>
                        <div>Name Product <input type="text"></div>
                        <div>Dimension Product <input type="text"></div>
                        <div>Date <input type="date"></div>
                        <div>Quantity <input type="text"></div>
                        <div>Amount <input type="text"></div>
                        <div>Paid<input type="text"></div>
                        <div>Owed <input type="text"></div>
                        <select>
                            <c:forEach items="${requestScope.products}" var="p">
                            <option>${p.name}</option>       
                            </c:forEach>
                        </select>
                        <input type="submit" value="Save">
                    </form>
                </div>-->

                <!-- report owed -->
<!--                <div id="owed" class="owed">
                    <form action="" method="get">
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
            </div>-->
        </div>
        <script>
            document.getElementById("btn-search").addEventListener("click", function() {
                document.getElementById("search").style.display = "block";
                document.getElementById("insert").style.display = "none";
                document.getElementById("owed").style.display = "none";
            });
            document.getElementById("btn-insert").addEventListener("click", function() {
                document.getElementById("search").style.display = "none";
                document.getElementById("insert").style.display = "block";
                document.getElementById("owed").style.display = "none";
            });
            document.getElementById("btn-owed").addEventListener("click", function() {
                document.getElementById("search").style.display = "none";
                document.getElementById("insert").style.display = "none";
                document.getElementById("owed").style.display = "block";
            });
        </script>
    </body>
</html>
