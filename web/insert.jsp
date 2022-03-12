<%-- 
    Document   : insert
    Created on : Mar 10, 2022, 4:32:17 PM
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
        <title>Insert Invoice</title>
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
                <!-- insert -->
                <div style="display:block;" id="insert" class="insert">
                    <form action="insert" method="POST">                      
                        <table>
                            <tr>
                                <td>Order by</td>
                                <td>
                                    <select name="idBuyer">
                                        <c:forEach items="${requestScope.buyers}" var="b">
                                            <option value="${b.id}">${b.name}${" "} ${b.dob}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="button">New Buyer</button>
                                </td>
                            </tr>
                            <tr>
                                <td>Product</td>
                                <td>
                                    <select name="idProduct">
                                        <c:forEach items="${requestScope.products}" var="p">
                                            <option value="${p.id}">${p.name}</option>       
                                        </c:forEach>
                                    </select>
                                    <button type="button">New Product</button>
                                </td>
                            </tr>
                            <tr>
                                <td>Dimension</td>
                                <td>
                                    <select name="idDimension">
                                        <c:forEach items="${requestScope.dimensions}" var="d">
                                            <option value="${d.id}">${d.name}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="button">New Dimension</button>
                                </td>
                            </tr>
                            <tr>
                                <td>Date</td>
                                <td><input type="datetime-local" name="date" required/></td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td><input type="number" name="buyPrice" required/></td>
                            </tr>
                            <tr>
                                <td>Quantity</td>
                                <td><input type="number" name="quantity" required/></td>
                            </tr>
                            <tr>
                                <td>Amount</td>
                                <td><input type="number" name="amount" required/></td>
                            </tr>
                            <tr>
                                <td>Paid</td>
                                <td><input type="number" name="paid" required/></td>
                            </tr>
                            <tr>
                                <td>Owed</td>
                                <td><input type="text" name="owed" required/></td>
                            </tr>
                            <tr>
                                <td>Name Agent</td>
                                <td>
                                    <select name="idAgency">
                                        <c:forEach items="${requestScope.agencies}" var="a">
                                            <option value="${a.id}">${a.name}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="button">New Agent</button>
                                </td>
                            </tr>
                        </table>
                        <input style="margin-top: 10px" type="submit" value="Save">
                    </form>
                </div>              
            </div>
        </div>
        <script>
            
        </script>
    </body>
</html>

