<%-- 
    Document   : depot
    Created on : Mar 13, 2022, 9:55:41 AM
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
        <title>Warehouse</title>
    </head>
    <body>

        <div class="container">
            <div class="header">
                <div class="header-title">Quản Lý Nhập Vật Liệu</div>

                <ul class="header-menu">
                    <li id="btn-search"><a href="home">Home</a></li>
                    <li id="btn-insert"><a href="insert">Insert Invoice</a></li>
                    <li id="btn-owed"><a href="owed">Invoices Owed</a></li>
                    <li id="btn-warehouse"><a href="warehouse">Warehouse</a></li>
                </ul>

                <div class="user-info">
                    <div class="user-name">
                        <i class="fa-solid fa-user"></i>
                        <span>${sessionScope.account.displayname}</span>
                        <i class="fa-solid fa-caret-down"></i>

                    </div>
                    <ul class="user-menu">
                        <li><a href="role">Set Role</a></li>
                        <li><a href="login">Log Out</a></li>
                    </ul>
                </div>
            </div>
            <div class="content">
                <div class="depot">
                    <form action="warehouse" method="GET">
                        <div style="text-align: center; margin-bottom: 20px">
                            Product 
                            <select name="idProduct">
                                <option value="-1">All</option>
                                <c:forEach items="${requestScope.products}" var="p">
                                    <option ${(requestScope.idProduct == p.id)?"selected":""} value="${p.id}">${p.name}</option>
                                </c:forEach>
                            </select>
                            Dimension 
                            <select name="idDimension">
                                <option value="-1">All</option>
                                <c:forEach items="${requestScope.dimensions}" var="d">
                                    <option ${(requestScope.idDimension == d.id)?"selected":""} value="${d.id}">${d.name}</option>
                                </c:forEach>
                            </select>
                            <input type="submit" value="Search"/>
                        </div>
                    </form>
                    <h2 style="text-align: center;">Warehouse</h2>
                    <table>
                        <tr>
                            <td>Product</td>
                            <td>Dimension</td>
                            <td>Total Quantity</td>
                        </tr>
                        <c:forEach items="${requestScope.productsDetail}" var="pd">
                            <tr>
                                <td>${pd.product.name}</td>
                                <td>${pd.dimension.name}</td>
                                <td>${pd.totalQuantity} ${"Tấm"}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
