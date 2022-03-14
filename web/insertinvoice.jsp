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
            <div class="header">
                <div class="header-title">Quản Lý Nhập Vật Liệu</div>

                <ul class="header-menu">
                    <li id="btn-search"><a href="home">Home</a></li>
                    <li id="btn-insert"><a href="insert">Insert</a></li>
                    <li id="btn-owed"><a href="owed">Report Owed</a></li>
                    <li id="btn-depot"><a href="depot">Depot</a></li>
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
            <div class="content">               
                <div id="insert" class="insert">
                    <form action="insert" method="POST">                      
                        <table>
                            <tr>
                                <td>Order by</td>
                                <td>
                                    <select name="idBuyer">
                                        <c:forEach items="${requestScope.buyers}" var="b">
                                            <option value="${b.id}">${b.name}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="button"><a href="buyer/insert">New Buyer</a></button>
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
                                    <button type="button"><a href="product/insert">New Product</a></button>
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
                                    <button type="button"><a href="dimension/insert">New Dimension</a></button>
                                </td>
                            </tr>
                            <tr>
                                <td>Date</td>
                                <td><input type="date" name="date" required/></td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td><input type="number" name="buyPrice" min="0" required/></td>
                            </tr>
                            <tr>
                                <td>Quantity</td>
                                <td><input type="number" name="quantity" min="0" required/></td>
                            </tr>
                            <tr>
                                <td>Discount</td>
                                <td><input type="number" name="discount" min="0" max="100" required/></td>
                            </tr>
                            <tr>
                                <td>Amount</td>
                                <td><input type="number" name="amount" required/></td>
                            </tr>
                            <tr>
                                <td>Paid</td>
                                <td><input type="number" name="paid" min="0" required/></td>
                            </tr>
                            <tr>
                                <td>Owed</td>
                                <td><input type="number" name="owed" min="0"  required/></td>
                            </tr>
                            <tr>
                                <td>Name Agency</td>
                                <td>
                                    <select name="idAgency">
                                        <c:forEach items="${requestScope.agencies}" var="a">
                                            <option value="${a.id}">${a.name}</option>
                                        </c:forEach>
                                    </select>
                                    <button type="button"><a href="agency/insert">New Agency</a></button>
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

