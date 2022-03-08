<%-- 
    Document   : home
    Created on : Mar 5, 2022, 12:28:18 AM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <li><a href="#">Search</a></li>
                    <li><a href="#">Insert</a></li>
                    <li><a href="#">Report Owed</a></li>
                </ul>

                <div class="user-info">
                    <div class="user-name">
                        <i class="fa-solid fa-user"></i>
                        <span>Phí Minh Tiến</span>
                        <i class="fa-solid fa-caret-down"></i>

                    </div>
                    <ul class="user-menu">
                        <li><a href="#">Log Out</a></li>
                    </ul>
                </div>
            </div>
            <!-- body -->
            <div class="content">
                <!-- search -->
                <div class="search">
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
                    <div class="page"><input type="text" value="1" onkeyup="keyUp(e)">/10</div>
                </div>

                <!-- insert -->
                <div class="insert">
                    <form action="" method="post">
                        <div>Customer <input type="text"></div>
                        <div>Name Material <input type="text"></div>
                        <div>Order Date <input type="date"></div>
                        <div>Ship Date <input type="date"></div>
                        <div>Quantity <input type="text"></div>
                        <div>Amount <input type="text"></div>
                        <div>Pay <input type="text"></div>
                        <div>Owed <input type="text"></div>
                        <div>Seller <input type="text"></div>
                        <input type="submit" value="Save">
                    </form>
                </div>

                <!-- report owed -->
                <div class="owed">
                    <form action="" method="get">
                        <div>From <input type="datetime" name="dateFrom"></div>
                        <div>To <input type="datetime" name="dateTo"></div>
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
                <!-- update -->
                <div class="update">

                </div>
            </div>
        </div>
    </body>
</html>
